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
	

//	private EntityManagerFactory entityManagerFactory;
	// 因為在 servlet 中有一個 base servlet 可以去繼承跟 access
	private EntityManager entityManager;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	// create constructor from super class
	public UserServices(EntityManager entityManager,HttpServletRequest request,HttpServletResponse response) {
		// persistence name 
		this.request = request;
		this.response = response;
//		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
//		entityManager = entityManagerFactory.createEntityManager();
		
		
		this.entityManager = entityManager;
		
		userDAO = new UserDAO(entityManager);
		
	}

	// 不同參數的 function
	public  void listUser() // 沒有給任何參數的
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
		
		// 確認 email 是否有重複 有時候 create 的時候 email 會重複
		Users existUser = userDAO.findByEmail(email);
		if(existUser != null) { // 如果有 則導向其他頁
			String eromessage = "Could not create user.</br> A user with email " 
					+ email +" has already exists";
			// setAttribute 的概念是 從 request 中設定一個名稱的參數
			request.setAttribute("eromessage", eromessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else { // 如果沒有 則可以正常創建
			Users newUser = new Users(email,fullname,password);
			userDAO.create(newUser); // 呼叫 userdao 中的 create 操作資料庫
			listUser("New User "+ email +" created successfully");
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
		String msg = "";
		if(userId == 1) { // admin 所以不能刪除 也可以做在 client side 但是 hacker 可以透過
			// url 的方式刪除 delete_user?id=1
			msg = "User admin can not be deleted";
			request.setAttribute("eromessage", msg);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
			
		}
		
		Users user = userDAO.get(userId);
		if(user == null) { // 如果同時有 admin 已經先瀏覽過 user 並刪除 id 了，則會顯示這個
			msg = "Could not find user" + userId + ", or it might be deleted by another admin";
			request.setAttribute("erromessage",msg);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		else { 
			userDAO.delete(userId);
			msg = "User "+ userId + " has been deleted successfully";
			listUser(msg); // list user message page
		}
		
	
	}
	
}
