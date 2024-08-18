<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Basic Reset */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://media.istockphoto.com/id/1294763851/vector/empty-blank-light-cream-or-beige-coloured-grunge-textured-vector-backgrounds.jpg?s=612x612&w=0&k=20&c=EWS5Pi3yUrPlWxICASa0b1E5oNGDD58twPyLRFTKhco='); /* Background image */
            background-size: cover;
            background-position: center;
            color: #333; 
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: rgba(300, 300, 300, 0.8); 
            backdrop-filter: blur(8px); 
            color: #FAF9F6; 
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
            padding: 0 20px;
            box-sizing: border-box;
        }
        .navbar .left-menu,
        .navbar .right-menu {
            display: flex;
            align-items: center;
        }
        .navbar a {
            color: #5C4033 ; 
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 18px;
            transition: background-color 0.3s ease, color 0.3s ease; /* Smooth transition for hover effect */
        }
        .navbar a:hover {
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent dark background on hover */
            color: #ffffff; /* White text on hover */
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
            padding-top: 60px; /* Adjust padding to account for navbar height */
            max-width: 1000px; /* Maximum width of content */
            margin: 0 auto; /* Center content horizontally */
        }
        .dashboard-container {
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%; /* Full width within content area */
            box-sizing: border-box;
            background-color: #ffffff; /* White background for dashboard container */
        }
        .dashboard-container img {
            max-width: 100%;
            height: auto;
            border-radius: 8px; /* Optional: Rounded corners for the image */
        }
        .dashboard-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #FF6600; /* Orange color for headings */
        }
        .dashboard-container a {
            display: block;
            margin: 15px 0;
            padding: 10px;
            background-color: #FF6600; /* Orange background for links */
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
        }
        .dashboard-container a:hover {
            background-color: #cc5200; /* Darker orange for hover effect */
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
            <a class="navbar-brand" href="Customer.jsp">
                <img src="logo.jpg" alt="Bank Logo"></a>
            <a href="PassbookController"><i class="fas fa-book icon"></i> Passbook</a>
            <a href="NewTransactionController"><i class="fas fa-plus-circle icon"></i> New Transaction</a>
        </div>
        <div class="right-menu">
            <div class="dropdown">
                <a href="#" class="dropbtn"><i class="fas fa-user-cog icon"></i></a>
                <div class="dropdown-content">
                    <a href="Customer.jsp?page=EditProfile">Edit Profile</a>
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
                if (userType == null || !"customer".equalsIgnoreCase(userType)) {
                    response.sendRedirect("Login.jsp");
                    return;
                }
            %>

            <!-- Display an Image Instead of Choose Option -->
            <c:choose>
                <c:when test="${param.page == 'ViewPassbook'}">
                    <jsp:include page="ViewPassbook.jsp"/>
                </c:when>
                <c:when test="${param.page == 'NewTransaction'}">
                    <jsp:include page="NewTransaction.jsp"/>
                </c:when>
                <c:when test="${param.page == 'EditProfile'}">
                    <jsp:include page="EditProfile.jsp"/>
                </c:when>
               
                <c:otherwise>
                    <!-- Welcome Image -->
                    <h1>Hello Customer</h1>
                    <img src="https://cdni.iconscout.com/illustration/premium/thumb/bank-customer-relationship-illustration-download-in-svg-png-gif-file-formats--partnership-financial-business-deal-pack-illustrations-7687844.png?f=webp" alt="Welcome Image" class="center"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
