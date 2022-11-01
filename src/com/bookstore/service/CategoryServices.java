package com.bookstore.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import com.bookstore.dao.CategoryDAO;

import com.bookstore.entity.Category;

// in the service class, we need to import DAO, entity, servlet ,Exception

public class CategoryServices {

//	因為都有 duplicate code about entity management
	// so we can create a super class contains the super classes
	// service are used by servlet
//	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.entityManager = entityManager; // need return

//		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
//		entityManager = entityManagerFactory.createEntityManager();

		categoryDAO = new CategoryDAO(entityManager);
	}

	// 實作 list category
	public void listCategory() throws ServletException, IOException {

		// 串接 dao
		// category dao 中定義的 list all 在 entity 的 namequery 中 依照字母順去做排列
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		// pass to jsp 轉位址
		String categoryUrl = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(categoryUrl);
		requestDispatcher.forward(request, response); // 這邊需要 throw exception

	}
	
	public void listCategory(String message) throws ServletException, IOException {

		// 串接 dao
		// category dao 中定義的 list all 在 entity 的 namequery 中 依照字母順去做排列
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if(message != null) {
			request.setAttribute("message",message);// display the success message font arg is the obj name, back arg is value

		}
		// pass to jsp 轉位址
		String categoryUrl = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(categoryUrl);
		requestDispatcher.forward(request, response); // 這邊需要 throw exception

	}

	public void createcategory() throws ServletException, IOException {

		// 先從前端抓輸入的 parameter
		String name = request.getParameter("name");

		// check whether is unique thus we need to find the same name of it
		Category existcat = categoryDAO.findByName(name);
		
		if (existcat != null) {
			String errormsg = "Could not create category.</br> A category with name " 
					+ name +" has already exists";
			request.setAttribute("eromessage", errormsg); // 前者的 string name 需要符合前端的變數名稱
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp"); // 要轉的 message page
			requestDispatcher.forward(request, response);
			
		}
		else { // 如果沒有就可以新增 persist category into db 
			
			Category cat = new Category(name);
			categoryDAO.create(cat);
			String message = "New category created sccessfully";
			listCategory(message);// we need to call list category to refresh the category page
		}

	

	}
	
	public void editCategory() {
		
	}

}
