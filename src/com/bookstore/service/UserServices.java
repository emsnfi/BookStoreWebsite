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
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	// create constructor from super class
	public UserServices(HttpServletRequest request,HttpServletResponse response) {
		// persistence name 
		this.request = request;
		this.response = response;
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
		entityManager = entityManagerFactory.createEntityManager();
		
		userDAO = new UserDAO(entityManager);
		
	}

	// 不同參數的 function
	public  void listUser()
	throws ServletException, IOException
		{
		listUser(null);
		
		}
	
	// 傳入動作訊息
	public  void listUser(String message)
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
	
	public void createUser()
			throws ServletException, IOException{
		
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		// 確認 email
		Users existUser = userDAO.findByEmail(email);
		if(existUser != null) { // 如果有 則導向其他頁
			String eromessage = "Could not create user.</br> A user with email " 
					+ email +" already exists";
			
			request.setAttribute("eromessage", eromessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else { // 如果沒有 則可以正常創建
			Users newUser = new Users(email,fullname,password);
			userDAO.create(newUser);
			listUser("New User created successfully");
		}
		
	}
	// 抓取要修改哪一個 user id
	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		
		Users user = userDAO.get(userId);
		// 要傳回去該 user 的詳細資訊
		String editPage = "user_form.jsp";
		
		request.setAttribute("user",user); // 給 front end 用的
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
		
	}
	
	// 修改
	public void updateUser() throws ServletException, IOException {
		// need to get details of the user in jsp form
		// getParameter 中的名稱是 input 標籤中 name 的名稱 使用 user_list.jsp 的名稱
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email"); 
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
//		System.out.println(userId+": "+email + ", "+fullName+", "+password);
		// 檢查修改後的 email 是否跟原本的 user 其他列中的資料相同
		Users userById = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);
		// 第一個是用 email 去看有無重複，但如果沒有變更 email 則有可能是找到自己，所以第二個判斷
		// 為看找到的是否是自己，如果不是 則就代表是重複的
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()){
			String eromessage = "Could not update user, it has duplicate </br>"+email+" already exists.";
			request.setAttribute("eromessage",eromessage);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}	
		else {
			Users user = new Users(userId,email,fullName,password);
			// 更新資料庫
			userDAO.update(user);
			 
			//更新
			String message = "User has been updated successfully";
			listUser(message);
		}
		
		
		
		
	}

	public void deleteUser() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("id"));
		if(userId == 1) { // admin 所以不能刪除
			String msg = "User admin can not be deleted";
			request.setAttribute("eromessage", msg);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
			
		}
		
		userDAO.delete(userId);
		String deletemessage = "User "+ userId + " has been deleted successfully";
		listUser(deletemessage);
	
	}
	
}
