package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

		    if (session == null || !"admin".equalsIgnoreCase((String) session.getAttribute("type"))) {
		        response.sendRedirect("Login.jsp");
		        return;
		    }

		    String action = request.getParameter("action");
		    RequestDispatcher dispatcher = null;

		    if (action == null) {
		        action = "default";
		    }

		    switch (action) {
		        case "addCustomer":
		            dispatcher = request.getRequestDispatcher("/AddCustomer.jsp");
		            break;
		        case "viewCustomers":
		            dispatcher = request.getRequestDispatcher("/ViewCustomers.jsp");
		            break;
		        case "addBankAccount":
		            dispatcher = request.getRequestDispatcher("/AddBankAccount.jsp");
		            break;
		        case "viewTransactions":
		            dispatcher = request.getRequestDispatcher("/ViewTransactions.jsp");
		            break;
		        default:
		            dispatcher = request.getRequestDispatcher("/AdminDashboard.jsp");
		            break;
		    }

		    dispatcher.include(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
