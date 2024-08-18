package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techlabs.entity.BankAccount;
import com.techlabs.entity.Customer;
import com.techlabs.model.CustomerOperator;
import com.techlabs.model.BankAccountOperator;

@WebServlet("/AddBankAccountController")
public class AddBankAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerOperator customerOperator = new CustomerOperator();
        List<Customer> customers = customerOperator.getCustomers();
        
        if (customers == null || customers.isEmpty()) {
            
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getCustomerId());
            }
        }
        
        request.setAttribute("customers", customers);

        
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam != null && !customerIdParam.isEmpty()) {
            int customerId = Integer.parseInt(customerIdParam);
            Customer selectedCustomer = null;
            
            for (Customer customer : customers) {
                if (customer.getCustomerId() == customerId) {
                    selectedCustomer = customer;
                    break;
                }
            }
            request.setAttribute("customer", selectedCustomer);
        }

        request.getRequestDispatcher("Admin.jsp?page=AddBankAccount").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        long accountNumber = generateRandomAccountNumber();
        String accountType = request.getParameter("accountType");
        double balance = Double.parseDouble(request.getParameter("balance"));
        
        BankAccount bank = new BankAccount(customerId, accountNumber, accountType, balance);

        BankAccountOperator bankAccountOperator = new BankAccountOperator();
        boolean success = bankAccountOperator.addBankAccount(bank);

        if (success) {
        	 request.setAttribute("successMessage", "Account added successfully.");
          
        } else {
        	 request.setAttribute("errorMessage", "Failed to add account. Please try again.");
        }
        request.getRequestDispatcher("Admin.jsp?page=AddBankAccount").forward(request, response);
    }

    private long generateRandomAccountNumber() {
        return (long) (Math.random() * 1_000_000_00000L);
    }
}
