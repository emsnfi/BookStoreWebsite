package com.bookstore.controller.admin.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.service.*;

import com.bookstore.controller.admin.*; // servlet
/**
 * Servlet implementation class CreateCategoryServlet
 */
@WebServlet("/admin/create_category")
// 路徑

public class CreateCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	// 需要
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryServices categoryservices = new CategoryServices(entityManager,request,response);
		categoryservices.createcategory();
	}

}
