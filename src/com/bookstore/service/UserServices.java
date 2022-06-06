package com.bookstore.service;

// 需要呼叫 userDAO
import com.bookstore.dao.*;
import javax.persistence.*; // 使用 persistence 中的 entity manager


import com.bookstore.entity.*;

import java.util.*;
public class UserServices {
	

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private UserDAO userDAO;
	// create constructor from super class
	public UserServices() {
		// persistence name 
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
		entityManager = entityManagerFactory.createEntityManager();
		
		userDAO = new UserDAO(entityManager);
		// TODO Auto-generated constructor stub
	}

	public  List<Users> listUser() {
		List<Users> listUsers = userDAO.listAll();
		
		
		return listUsers;
	}
	
	
	
}
