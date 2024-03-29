package com.bookstore.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
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

	// 實作 list category no message to pass in
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
	
	public void editCategory() throws ServletException, IOException{
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		String despage ="";
		// have to check whether it exist
		if(category != null) {
			request.setAttribute("category",category);
			
			
			// after define, ues dispatcher to pass
			 despage = "category_form.jsp";
			
		}
		else { // possible scenerio :admin1 & admin2 can view at same time and admin1 delete the category
			// while admin2 edit the category, which has been deleted
			String message = "Could not find the category, it might be deleted~";
			request.setAttribute("message", message);
			despage = "messge.jsp";
		}
		
		// request attribute
		// setAttribute(name,value) value 為對應到 前端要傳入的數值
		// 這邊為 category_form 會使用到的 category value
		// 前端要引入 <!-- 為引用 jstl  -->	
//		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(despage); 
		requestDispatcher.forward(request, response);
		
		
		
	}
	
	public void updateCategory() throws ServletException,IOException{
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name"); 
		
//		Category category = categoryDAO.get(categoryId); // current selected category
		
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName); // 從輸入的 category name catch the object
		
		// if category name 不是空的 表示已經有該 category name (重複), if id diff to the id 表示是指不同的物件項目
		// 如果是相同的物件項目 則 category name 就會是一樣，所以要排除這個可能性
		if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String eromessage = "Could not update category." + 
					" A category with name "+ categoryName +" already exists.";
			
			// name,value
			request.setAttribute("eromessage",eromessage);
			
			String path = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
		}
		else {
			// or we can update it
			categoryById.setName(categoryName); // 將抓取到的 id 使用 set name 方式更改 category name
			categoryDAO.update(categoryById);
			String message = "Category " + categoryName +" has updated successfully!";
			
			
			listCategory(message);
		}
	}
	
	public void deleteCategory() throws ServletException,IOException{
		// first get the category id
		int categoryId = Integer.parseInt(request.getParameter("id")); // why is id? others are categoryId
		categoryDAO.delete(categoryId);
//		Category category = categoryDAO.get(categoryId);
		
		String message ="The category with ID " + categoryId + " has been removed successfully.";
		listCategory(message);
		
		// leave the checking for books belongs to category later-> because catgory can have one or more book
	}

}
