package com.techlabs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techlabs.model.CustomerOperator;
import com.techlabs.entity.Customer;

@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);

        if (customer == null) {
            response.sendRedirect("Login.jsp"); 
            return;
        }

        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            CustomerOperator customerOperator = new CustomerOperator();
            String errorMessage = null;
            String successMessage = null;

            if (newPassword != null && !newPassword.isEmpty()) {
                // Validate current password
                if (!customerOperator.validatePassword(customer.getUserId(), currentPassword)) {
                	
                	customerOperator.validatePassword(customer.getUserId(), currentPassword);
                    errorMessage = "Current password is incorrect.";
                } else if (!newPassword.equals(confirmPassword)) {
                    errorMessage = "New passwords do not match.";
                } else {
                	
                    
                    customerOperator.updatePassword(customer.getUserId(), newPassword);
                }
            }

            if (errorMessage == null) {
                
                customerOperator.updateCustomerProfile(customer.getUserId(), firstName, lastName);

               
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                session.setAttribute("customer", customer);

                successMessage = "Profile updated successfully.";
            }

            request.setAttribute("error", errorMessage);
            request.setAttribute("success", successMessage);
            request.getRequestDispatcher("Homepage.jsp").forward(request, response);
        }
    }
}
