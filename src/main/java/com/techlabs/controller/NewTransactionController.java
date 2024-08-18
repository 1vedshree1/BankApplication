package com.techlabs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.BankAccount;
import com.techlabs.model.BankAccountOperator;

/**
 * Servlet implementation class NewTransactionController
 */
@WebServlet("/NewTransactionController")
public class NewTransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTransactionController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        BankAccountOperator operator = new BankAccountOperator();
        List<BankAccount> accounts = operator.getAccounts(customerId);
        List<BankAccount> otherAccounts = operator.getOtherAccounts(customerId);

        request.setAttribute("accounts", accounts);
        request.setAttribute("otherAccounts", otherAccounts);

        request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transactionType = request.getParameter("transactionType");
        String selectedAccountNumberStr = request.getParameter("accountId");
        String amountStr = request.getParameter("amount");
        String transferAccountNumberStr = request.getParameter("transferaccount_number");

        
        long selectedAccountNumber = 0;
        double amount = 0.0;
        long transferAccountNumber = 0;

        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        BankAccountOperator operator = new BankAccountOperator();
        
        
        if (selectedAccountNumberStr != null && !selectedAccountNumberStr.trim().isEmpty()) {
            try {
                selectedAccountNumber = Long.parseLong(selectedAccountNumberStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid account number format.");
                request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "Account number is required.");
            request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
            return;
        }

        
        if (amountStr != null && !amountStr.trim().isEmpty()) {
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid amount format.");
                request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "Amount is required.");
            request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
            return;
        }

        
        if (transactionType != null && "transfer".equals(transactionType) && transferAccountNumberStr != null && !transferAccountNumberStr.trim().isEmpty()) {
            try {
                transferAccountNumber = Long.parseLong(transferAccountNumberStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid transfer account number format.");
                request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
                return;
            }
        }

        BankAccount account = operator.getAccountByNumber(selectedAccountNumber);
        int selectedAccountId = operator.getAccountId(selectedAccountNumber);

        boolean transactionSuccessful = false;

        switch (transactionType) {
            case "credit":
                operator.creditAccount(account, amount);
                transactionSuccessful = true;
                break;
            case "debit":
                operator.debitAccount(account, amount);
                transactionSuccessful = true;
                break;
            case "transfer":
                BankAccount transferAccount = operator.getAccountByNumber(transferAccountNumber);
                operator.transferFunds(account, transferAccount, amount);
                transactionSuccessful = true;
                break;
            default:
                request.setAttribute("errorMessage", "Invalid transaction type.");
                break;
        }

        if (transactionSuccessful) {
            request.setAttribute("successMessage", "Transaction completed successfully.");
            operator.saveTransaction(selectedAccountId, transactionType, amount, transferAccountNumber);
        }

        
        List<BankAccount> accounts = operator.getAccounts(customerId);
        List<BankAccount> otherAccounts = operator.getOtherAccounts(customerId);

        request.setAttribute("accounts", accounts);
        request.setAttribute("otherAccounts", otherAccounts);

        
        request.getRequestDispatcher("Customer.jsp?page=NewTransaction").forward(request, response);
    }
}
