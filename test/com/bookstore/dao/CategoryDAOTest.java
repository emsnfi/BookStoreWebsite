package com.bookstore.dao;

import static org.junit.Assert.*;
// need to be import 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.entity.Category;

//import antlr.collections.List;

public class CategoryDAOTest extends BaseDAOTest{
//	private static EntityManagerFactory entityManagerFactory; // 查詢取用範圍
//	private static EntityManager entityManager;
	private static CategoryDAO categoryDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 要啟動 entity
//		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
//		entityManager = entityManagerFactory.createEntityManager();
		BaseDAOTest.setUpBeforeClass();
		categoryDao = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	// close
//		entityManager.close();
//		entityManagerFactory.close();
		BaseDAOTest.tearDownClass();
	
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateCategory() {

		Category newCat = new Category("Health");
		Category category = categoryDao.create(newCat);
		
		// 訊息判斷
		assertTrue(category != null && category.getCategoryId() > 0);
		
	}

	@Test
	public void testUpdateCategory() {
//		fail("Not yet implemented");
		// category only name feild can be updated
		// 建立新的 category entity
		Category cat = new Category("ASP.net");// 給 category name 參數
		// 設定該 category 的 id （name update 成 ASP.net）
		cat.setCategoryId(2); 
		// 呼叫 categorydao 中的 update function
		Category category = categoryDao.update(cat);
		
		// 用相等確認 name 
		assertEquals(cat.getName(),category.getName());
		
	}

	@Test
	public void testGet() {
		// confirm categorydao 的 get function
//		fail("Not yet implemented");
		Integer catId = 2;
		Category cat = categoryDao.get(catId);
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		int catid = 3;
		// 先刪除，在用 get 看，是否還有該物件
		categoryDao.delete(catid);
		Category cat = categoryDao.get(catid);
		assertNull(cat);
		
	}
	
	@Test
	public void testListAll() {
//		Category cat = new Category("CC");
		
		List<Category> listCategory = categoryDao.listAll();
		// in the entity namequery it is sorted by category name not by id
		listCategory.forEach(c -> System.out.println(c.getName() + ","));
		assertTrue(listCategory.size() > 0);
//		return super.findWithNameQuery("cat.findAll");
	}

	@Test
	public void testCount() {
		long totalCategoryCount = categoryDao.count();
		assertEquals(4,totalCategoryCount);
	}

}
