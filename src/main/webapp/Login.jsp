<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
            margin: 0;
        }
        .login-container {
            background-color: #fff;
            padding: 10px;
            border-radius: 8px;
            
            width: 100%;
            max-width: 500px; /* Adjust the max width as needed */
        }
        .login-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
            text-align: center;
        }
        .login-container label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        .login-container input, .login-container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        .login-container button {
            width: 100%;
            padding: 12px;
            background-color: #5C4033; 
            border: none;
            color: #fff;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .login-container button:hover {
            background-color: #5C4011; 
        }
        .error-message {
            color: #dc3545;
            margin-bottom: 15px;
            font-size: 14px;
            text-align: center;
        }
        @media (max-width: 600px) {
            .login-container {
                padding: 15px;
                max-width: 100%;
            }
            .login-container h2 {
                font-size: 20px;
            }
            .login-container input, .login-container select {
                font-size: 14px;
            }
            .login-container button {
                font-size: 16px;
                padding: 10px;
            }
        }
    </style>
</head>
<body>

    <div class="login-container">
       
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message"><%= errorMessage %></div>
        <%
            request.removeAttribute("errorMessage"); 
            }
        %>
        <form action="LoginController" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="type">Type:</label>
            <select id="type" name="type" required>
                <option value="admin">Admin</option>
                <option value="customer">Customer</option>
            </select>

            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
