package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Transaction;
import com.techlabs.model.TransactionsOperator;

/**
 * Servlet implementation class PassbookController
 */
@WebServlet("/PassbookController")
public class PassbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassbookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        Integer customerId = (Integer) session.getAttribute("customerId");
	        String type = request.getParameter("type");
	        System.out.println(customerId);
	        System.out.println(type);
		RequestDispatcher dispatcher = null;
		TransactionsOperator transaction = new TransactionsOperator();
				transaction.connectToDb();
				 List<Transaction> transactions;
			        if (type == null || type.equals("all")) {
			            transactions = transaction.getTransactionsByCustomerId(customerId);
			        } else {
			            transactions = transaction.getFilteredTransactionsByCustomerId(customerId, type);
			        }
				System.out.println("Transactions fetched: " + transactions.size());
				request.setAttribute("transactions", transactions);
				dispatcher= request.getRequestDispatcher("Customer.jsp?page=ViewPassbook");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
