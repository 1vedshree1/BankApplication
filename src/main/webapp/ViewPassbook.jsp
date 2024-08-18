<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transaction Passbook</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9; 
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #ffffff; 
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
        h1 {
            text-align: center;
            color: #333;
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
    <h1>Transaction Passbook</h1>
    
     <!-- Filter Form -->
    <div class="filter-form">
        <form action="PassbookController" method="get">
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
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Account Number</th>
                <th>Transfer Account Number</th>
            </tr>
        </thead>
        <tbody>
            <!-- JSTL tag to iterate over transactions -->
            <c:forEach var="transaction" items="${transactions}">
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
        </tbody>
    </table>
    
</body>
</html>
