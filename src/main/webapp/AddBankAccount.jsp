<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Bank Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9; /* Light gray background */
        }
        .container {
            max-width: 900px;
            margin: 80px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h3 {
            margin-top: 0;
            color: #333;
        }
        h4 {
            margin-top: 20px;
            color: #333;
        }
        form {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }
        select,
        input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 15px;
            font-size: 16px;
        }
        button {
            background-color: #5C4033; /* Blue button */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #5C4033; /* Blue header background */
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9; /* Light gray background for even rows */
        }
        tr:hover {
            background-color: #f1f1f1; /* Slightly darker background on hover */
        }
        .customer-table-container {
            margin-top: 20px;
        }
        .more-options {
            display: none;
        }
        .show-more {
            cursor: pointer;
            color: #007bff;
            font-weight: bold;
            margin-top: 10px;
            display: block;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h3>Add Bank Account</h3>
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>
        <!-- Customer Selection Form -->
        <form action="AddBankAccountController" method="get">
            <label for="customerId">Select Customer:</label>
            <select id="customerId" name="customerId" onchange="this.form.submit()">
                <option value="">Select a Customer</option>
                <!-- Display first 3 customers initially -->
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <c:if test="${status.index < 3}">
                        <option value="${customer.customerId}"
                            <c:if test="${param.customerId == customer.customerId}">selected</c:if>>
                            ${customer.customerId} - ${customer.firstName} ${customer.lastName}
                        </option>
                    </c:if>
                </c:forEach>
                <!-- Hidden options for the rest of the customers -->
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <c:if test="${status.index >= 3}">
                        <option class="more-options" value="${customer.customerId}"
                            <c:if test="${param.customerId == customer.customerId}">selected</c:if>>
                            ${customer.customerId} - ${customer.firstName} ${customer.lastName}
                        </option>
                    </c:if>
                </c:forEach>
            </select>
            <!-- Link to show more options -->
            <a href="#" class="show-more" onclick="toggleMoreOptions(event)">Show more</a>
        </form>

        <!-- Display Customer Details -->
        <c:if test="${customer != null}">
            <h4>Customer Details</h4>
            <div class="customer-table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${customer.customerId}</td>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <h4>Create Bank Account</h4>
            <form action="AddBankAccountController" method="post">
                <input type="hidden" name="customerId" value="${customer.customerId}">
                
                <label for="accountType">Account Type:</label>
                <select id="accountType" name="accountType">
                    <option value="Savings">Savings</option>
                    <option value="Current">Current</option>
                </select>
                
                <label for="balance">Initial Balance:</label>
                <input type="number" id="balance" name="balance" step="0.01" min="0" placeholder="Enter initial balance">

                <button type="submit">Add Bank Account</button>
            </form>
        </c:if>
    </div>

    <script>
        function toggleMoreOptions(event) {
            event.preventDefault();
            var moreOptions = document.querySelectorAll('.more-options');
            moreOptions.forEach(function(option) {
                option.style.display = option.style.display === 'block' ? 'none' : 'block';
            });
            var link = document.querySelector('.show-more');
            link.textContent = link.textContent === 'Show more' ? 'Show less' : 'Show more';
        }
    </script>
</body>
</html>
