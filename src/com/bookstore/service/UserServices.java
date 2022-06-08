package com.bookstore.service;

// 需要呼叫 userDAO
import com.bookstore.dao.*;
import javax.persistence.*; // 使用 persistence 中的 entity manager
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.entity.*;

import java.io.IOException;
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

	// 不同參數的 function
	public  void listUser(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException
		{
		}
	public  void listUser(HttpServletRequest request,HttpServletResponse response,
			String message)
	throws ServletException, IOException
		{
		// 直接在這裡處理 HTTP request response 等
		List<Users> listUsers = userDAO.listAll();
		
		// name the attribute listuser  
		request.setAttribute("listUsers",listUsers);
		// 因為不是一直都在 只有建立後才會出現
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// 轉位址
		String listpage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listpage);
		requestDispatcher.forward(request,response);
		
		
	}
	
	public void createUser(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		Users newUser = new Users(email,fullname,password);
		userDAO.create(newUser);
	}
	
}
