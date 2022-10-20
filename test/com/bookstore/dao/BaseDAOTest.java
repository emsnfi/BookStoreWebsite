package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {
	// 統一
	protected static EntityManagerFactory entityManagerFactory; // 查詢取用範圍
	protected static EntityManager entityManager;
	
	
	public static void setUpBeforeClass() throws Exception {
		// 要啟動 entity
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		
	}
	public static void tearDownClass() {
		entityManager.close();
		entityManagerFactory.close();

	}
}
