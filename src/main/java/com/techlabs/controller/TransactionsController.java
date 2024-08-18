package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.techlabs.entity.Transaction;
import com.techlabs.entity.User;
import com.techlabs.model.TransactionsOperator;

/**
 * Servlet implementation class TransactionController
 */
@WebServlet("/TransactionsController")
public class TransactionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String type = request.getParameter("type");
TransactionsOperator transaction = new TransactionsOperator();
		transaction.connectToDb();
		 List<Transaction> transactions;
	        if (type == null || type.equals("all")) {
	        	transactions=transaction.getTransactions();
	        } else {
	            transactions = transaction.getTransactionsByType(type);
	        }
		
		System.out.println("Transactions fetched: " + transactions.size());
		request.setAttribute("transactions", transactions);
		dispatcher= request.getRequestDispatcher("Admin.jsp?page=ViewTransactions");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
