package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

// 有關 test 的 package
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

// other util
import java.util.*;
public class UserDAOTest {

	private static EntityManagerFactory entityManagerFactory; // 查詢取用範圍
	private static EntityManager entityManager;
	private static UserDAO userDAO;

	@BeforeClass
	// 可以初始化一些 resource 避免後面測試重複宣告等
	// 例如 entityManager 的 create and close
	public static void setupClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}

	// 只 test create
	@Test
	public void testCreateUsers() {
		// 建立值
		Users user1 = new Users();
		user1.setEmail("toggle@gmail.com");
		user1.setFullname("toggleLin");
		user1.setPassword("crtufd");

		// 建立 entitymanager factory
		// 建立 entity manager

		userDAO.create(user1);

		// 先 close entityManager 在 close entityManagerFactory

		assertTrue(user1.getUserId() > 0); // 如果有正確 create 則 id 一定大於 0

	}

	// its field are not 初始化
	// 因為 users table 的 every field 都不能是空(not null)
	// 所以要 throw 進 exception
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user1 = new Users();
		userDAO.create(user1);
		
		
//		assertTrue(user1.getUserId()>0); // 如果有正確 create 則 id 一定大於 0
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("emsnfi@gmail.com"); // 雖然沒有改變 但還是要寫，不然會變成 null
		user.setPassword("good");
		user.setFullname("Emily Lin");

		user = userDAO.update(user);
		// verify the update
		// 只改了 password 所以 確認 password 的值是否一樣
		String expected = "good";
		String actual = user.getPassword();
		assertEquals(expected,actual);
		
}
	
	@Test // 找物件 有該 id 的狀況
	public void testgetUsersFound() {
//		Integer userId = 1; // Integer 跟 int 的不同
		int userId = 1;  
		Users user = userDAO.get(userId);
		
		// 如果有找到 才會印出
		if(user != null) {
			System.out.print(user.getEmail());
		}
		assertNotNull(user);
		
		
	}
	@Test
	public void testDeleteUsers() {
		int userId = 5;
		userDAO.delete(userId);
		Users user = userDAO.get(userId);
		assertNull(user);
		
	}
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		int userId = 55; // 因為不存在，所以要 throw exception
		userDAO.delete(userId);
		
	}
	 
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		// Users is the entity
		for(Users user:listUsers) {
			System.out.println(user.getFullname());
		}
		
//		System
		assertTrue(listUsers.size()>0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertEquals(5,totalUsers);
	}
	
	@AfterClass // clean the resource
	public static void tearDownClass() {
		entityManager.close();
		entityManagerFactory.close();

	}
}
