<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Basic Reset */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://media.istockphoto.com/id/1294763851/vector/empty-blank-light-cream-or-beige-coloured-grunge-textured-vector-backgrounds.jpg?s=612x612&w=0&k=20&c=EWS5Pi3yUrPlWxICASa0b1E5oNGDD58twPyLRFTKhco=');
            background-size: cover;
            background-position: center;
            color: #333;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: rgba(255, 255, 255, 0.8); 
            backdrop-filter: blur(8px); 
            color: #FAF9F6; 
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
            padding: 0 20px;
            box-sizing: border-box;
            height: 60px; /* Navbar height */
        }
        .navbar .left-menu,
        .navbar .right-menu {
            display: flex;
            align-items: center;
        }
        .navbar a {
            color: #5C4033; 
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 18px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .navbar a:hover {
            background-color: rgba(0, 0, 0, 0.3); 
            color: #ffffff; 
        }
         .navbar-brand img {
            height: 40px; /* Adjust the logo height as needed */
        }
        .dropdown {
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            right: 0;
            background-color: #ffffff;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1000;
            min-width: 160px;
            border-radius: 4px;
        }
        .dropdown-content a {
            color: #333;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }
        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .content {
            padding-top: 60px; 
            max-width: 1000px; 
            margin: 0 auto; 
            padding: 20px;
        }
        .dashboard-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%; 
            box-sizing: border-box;
        }
        .dashboard-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }
        .dashboard-container a {
            display: block;
            margin: 15px 0;
            padding: 10px;
            background-color: #007bff; 
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
        }
        .dashboard-container a:hover {
            background-color: #0056b3; 
        }
        .dashboard-container .icon {
            margin-right: 8px;
        }
        .center {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 50%;
        }
        
        
    </style>
</head>
<body>
    <!-- Navbar -->
    <div class="navbar">
        <div class="left-menu">
        <a class="navbar-brand" href="Admin.jsp">
                <img src="logo.jpg" alt="Bank Logo"></a>
        
            <a href="Admin.jsp?page=AddCustomer"><i class="fas fa-user-plus icon"></i> Add New Customer</a>
            <a href="ViewCustomerController"><i class="fas fa-users icon"></i> View Customers</a>
            <a href="AddBankAccountController"><i class="fas fa-university icon"></i> Add Bank Account</a>
            <a href="TransactionsController"><i class="fas fa-money-bill icon"></i> View Transactions</a>
        </div>
        <div class="right-menu">
            <div class="dropdown">
                <a href="#" class="dropbtn"><i class="fas fa-user-cog icon"></i></a>
                <div class="dropdown-content">
                    <a href="LogOutController">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div>
            <%
                String userType = (String) session.getAttribute("type");
                if (userType == null || !"admin".equalsIgnoreCase(userType)) {
                    response.sendRedirect("Login.jsp");
                    return;
                }
            %>
            <div class="form-container">
                <!-- Display messages -->
                <c:if test="${not empty successMessage}">
                    <div class="message success">
                        <c:out value="${successMessage}"/>
                    </div>
                </c:if>

                <c:if test="${not empty errorMessage}">
                    <div class="message error">
                        <c:out value="${errorMessage}"/>
                    </div>
                </c:if>
                <!-- Content Based on Query Parameter -->
                <c:choose>
                    <c:when test="${param.page == 'AddCustomer'}">
                        <jsp:include page="AddCustomer.jsp"/>
                    </c:when>
                    <c:when test="${param.page == 'ViewCustomer'}">
                        <jsp:include page="ViewCustomer.jsp"/>
                    </c:when>
                    <c:when test="${param.page == 'AddBankAccount'}">
                        <jsp:include page="AddBankAccount.jsp"/>
                    </c:when>
                    <c:when test="${param.page == 'ViewTransactions'}">
                        <jsp:include page="ViewTransaction.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <img src="https://cdni.iconscout.com/illustration/premium/thumb/bank-customer-relationship-illustration-download-in-svg-png-gif-file-formats--partnership-financial-business-deal-pack-illustrations-7687844.png?f=webp" alt="Dashboard Image" class="center"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
