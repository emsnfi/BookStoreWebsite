package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.service.*;
/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/admin/create_user")

public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // get value and bring to  db
		// 這邊對應到 jsp 會使用
		// 會抓 webservlet url 傳過來的資料

		UserServices userServices = new UserServices(request,response );
		userServices.createUser();
		
	
	}

}
