package com.bookstore.controller.admin.category;

import com.bookstore.controller.admin.BaseServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.*;

/**
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/admin/update_category")
public class UpdateCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCategoryServlet() {
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryServices categoryService = new CategoryServices(entityManager,request,response);
		
		categoryService.updateCategory();
		
	}

}
