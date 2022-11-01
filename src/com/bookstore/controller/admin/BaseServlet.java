package com.bookstore.controller.admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class BaseServlet
 */



// should be an abstract
// other servlet class should extend this base servlet
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// implement entity management
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	// implement method of generic service
	// lifecycle method of servlet
	
	@Override
	// init entity manager
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
		entityManager = entityManagerFactory.createEntityManager();
		
//		super.init();
	}
	
	@Override
	public void destroy() {
		// release entity manager
		entityManager.clear();
		entityManagerFactory.close();
	}
	
	

}
