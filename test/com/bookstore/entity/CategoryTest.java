package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.bookstore.entity.*;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;


public class CategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Category newCat = new Category("Advanced Java");
		
		
		
		// 建立 entitymanager factory
		// 建立 entity manager
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		
		
		// start transaction
		entityManager.getTransaction().begin();	
		
		// persist user object
		entityManager.persist(newCat);
		
		// commit the transaction
		entityManager.getTransaction().commit();
		
		//先 close entityManager 在 close entityManagerFactory
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("A Category object was persisted ");
		
		
}
	

}


