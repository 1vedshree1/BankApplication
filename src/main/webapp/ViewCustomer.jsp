<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customers</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://media.istockphoto.com/id/1294763851/vector/empty-blank-light-cream-or-beige-coloured-grunge-textured-vector-backgrounds.jpg?s=612x612&w=0&k=20&c=EWS5Pi3yUrPlWxICASa0b1E5oNGDD58twPyLRFTKhco=');
            background-size: cover;
            background-position: center;
            color: #333;
        }

        
        .content {
            padding-top: 60px; 
            max-width: 1200px;
            margin: 0 auto; 
            padding: 20px;
        }
        .container {
            background-color: #ffffff;
            padding: 70px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow-x: auto; /* Enables horizontal scroll if needed */
        }
        h3{
            margin-top: 15;
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            table-layout: fixed; /* Ensures columns stay in a fixed size */
        }
        table th,
        table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            overflow: hidden;
            text-overflow: ellipsis; 
            white-space: nowrap; 
        }
        table th {
            background-color: #5C4033;
            color: white;
        }
        table tr:nth-child(even) {
            background-color: #f9f9f9; 
        }
        table tr:hover {
            background-color: #e0dcd5; 
        }
        p {
            text-align: center;
            font-size: 18px;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h3>Customer List</h3>
        <p>Number of customers: ${fn:length(customerList)}</p>
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
                <c:forEach var="customer" items="${customerList}">
                    <tr>
                        <td>${customer.customerId}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
