package com.bookstore.controller.admin.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.admin.*;
import com.bookstore.service.*;
// mapping the servlet url
@WebServlet("/admin/list_category")

// 同時這邊也需要有 header.jsp 的 list category 連結 
public class ListCategoryServlet extends BaseServlet { // once extend servlet 
	// then we don't need to use entity manager in service class
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// getWriter :Returns a PrintWriter object that can send character text to the client.
//		response.getWriter().println("List category");
		
		// 有 extend base servlet 可以引用 entity manager
		response.getWriter().append("List category");
		// this entity manager is extend from base servlet
		CategoryServices categoryService = new CategoryServices(entityManager,request,response);
		// will call service class to deal with the
		categoryService.listCategory();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
