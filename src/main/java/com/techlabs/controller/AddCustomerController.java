package com.techlabs.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.entity.Customer;
import com.techlabs.model.CustomerOperator;


@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Customer customer=null;
       CustomerOperator operator=null;
    
    public AddCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("Admin.jsp?page=AddCustomer").forward(request, response);
            return;
        }

       
        if (!isStrongPassword(password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain a mix of letters, numbers, and special characters.");
            request.getRequestDispatcher("Admin.jsp?page=AddCustomer").forward(request, response);
            return;
        }
		
       
		customer= new Customer(firstName, lastName, email, password);
		operator = new CustomerOperator();
		
		boolean isAdded = operator.AddCustomer(customer);
		if (isAdded) {
            request.setAttribute("successMessage", "Customer added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add account. Please try again.");
        }
		request.getRequestDispatcher("Admin.jsp?page=AddCustomer").forward(request, response);
    }
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

	public boolean isStrongPassword(String password) {
	    return password.length() >= 8 &&
	           password.chars().anyMatch(Character::isLowerCase) &&
	           password.chars().anyMatch(Character::isUpperCase) &&
	           password.chars().anyMatch(Character::isDigit) &&
	           password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:',.<>?/`~".indexOf(ch) >= 0);
	}


}
