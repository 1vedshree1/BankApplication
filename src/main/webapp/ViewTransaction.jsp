<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> <!-- Optional: for icons -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9; /* Light gray background */
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 1200px; /* Limiting maximum width for better readability */
            padding: 80px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            overflow-x: auto; /* Allows horizontal scrolling if needed */
        }

        .header {
            margin-bottom: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th,
        table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #5C4033; 
            color: white;
            font-weight: bold;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9; /* Light gray background for even rows */
        }

        table tr:hover {
            background-color: #f1f1f1; /* Slightly darker background on hover */
        }

        table td {
            font-size: 14px;
            color: #555;
        }

        .no-data {
            text-align: center;
            color: #777;
            font-style: italic;
        }
        .filter-form {
            margin-bottom: 20px;
            text-align: center;
        }
        .filter-form label {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Transaction List</h1>
        </div>
         <!-- Filter Form -->
    <div class="filter-form">
        <form action="TransactionsController" method="get">
            <label>
                <input type="radio" name="type" value="all" ${param.type == 'all' ? 'checked' : ''}> All
            </label>
            <label>
                <input type="radio" name="type" value="credit" ${param.type == 'credit' ? 'checked' : ''}> Credit
            </label>
            <label>
                <input type="radio" name="type" value="debit" ${param.type == 'debit' ? 'checked' : ''}> Debit
            </label>
            <input type="submit" value="Filter">
        </form>
    </div>
    

        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Transaction Date</th>
                    <th>Transaction Type</th>
                    <th>Amount</th>
                    <th>Account Number</th>
                    <th>Transfer Account Number</th>
                </tr>
            </thead>
            <tbody>
                <!-- JSTL to loop through transactions -->
                <c:choose>
                    <c:when test="${not empty transactions}">
                        <c:forEach items="${transactions}" var="transaction">
                            <tr>
                                <td><c:out value="${transaction.transactionId}" /></td>
                                <td><c:out value="${transaction.transactionDate}" /></td>
                                <td>
                                <c:choose>
                            <c:when test="${transaction.transaction_type eq 'transfer'}">
                                debit
                            </c:when>
                            <c:otherwise>
                                <c:out value="${transaction.transaction_type}" />
                            </c:otherwise>
                        </c:choose>
                        </td>
                                <td><c:out value="${transaction.amount}" /></td>
                                <td><c:out value="${transaction.accountNumber}" /></td>
                                <td>
                                    <!-- Conditionally display "NA" if transfer_accountNumber is 0 -->
                                    <c:choose>
                                        <c:when test="${transaction.transfer_accountNumber == 0}">
                                            NA
                                        </c:when>
                                        
                                        <c:otherwise>
                                            <c:out value="${transaction.transfer_accountNumber}" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="no-data">No transactions found</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>
