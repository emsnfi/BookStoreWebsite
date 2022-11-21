package com.bookstore.controller.admin.category;

import com.bookstore.controller.admin.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.service.*;
/**
 * Servlet implementation class DeleteCategoyrServlet
 */
@WebServlet("/admin/delete_category")
public class DeleteCategoyrServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteCategoyrServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryServices categoryService = new CategoryServices(entityManager,request,response);
		
		categoryService.deleteCategory();
		
		
	}

}
