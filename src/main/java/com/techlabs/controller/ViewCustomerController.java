package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.Customer;
import com.techlabs.model.CustomerOperator;


@WebServlet("/ViewCustomerController")
public class ViewCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CustomerOperator customerOperator = new CustomerOperator();
	        List<Customer> customerList = customerOperator.getCustomers();

	        request.setAttribute("customerList", customerList);
	        System.out.println("Number of customers: " + customerList.size());
	        request.getRequestDispatcher("Admin.jsp?page=ViewCustomer").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
