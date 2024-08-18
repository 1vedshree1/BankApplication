package com.techlabs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Customer;
import com.techlabs.entity.User;
import com.techlabs.model.CustomerOperator;
import com.techlabs.model.UserValidator;



@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserValidator user=null;
   
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		HttpSession session=request.getSession();  
        
		user = new UserValidator();
		user.connectToDb();
		
		if(user.validateUser(username, password,type)) {
			session.setAttribute("username",username);
	        session.setAttribute("password", password);
			session.setAttribute("type", type);
			
			if (type.equalsIgnoreCase("customer")) {
				 int userId = user.getUserId(username, password); // You'll need to implement this method

	               System.out.println(userId);
	                int customerId = user.getCustomerIdByUserId(userId);
	                System.out.println(customerId);
	                session.setAttribute("userId", userId);
	                session.setAttribute("customerId", customerId);
	                
	                CustomerOperator customerOperator = new CustomerOperator();
	                Customer customer = customerOperator.getCustomerById(customerId);

	                // Store customer object in session
	                session.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("/Customer.jsp");
            } else if (type.equalsIgnoreCase("admin")) {
                dispatcher = request.getRequestDispatcher("/Admin.jsp");
            } 
			
        } 
		else {
			 request.setAttribute("errorMessage", "Invalid username or password.");
	          dispatcher = request.getRequestDispatcher("/Login.jsp");
        }
		
		dispatcher.forward(request, response);
			
				
		
		    
		 	
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
