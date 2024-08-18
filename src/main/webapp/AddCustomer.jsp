<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Customer</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9; 
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; 
        }

        .container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start; 
            width: 100%;
            max-width: 1200px; 
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-container,
        .content-container {
            flex: 1;
            margin: 10px;
        }

        .form-container {
            max-width: 900px;
        }

        h3 {
            margin-top: 0;
            color: #333;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 15px;
            font-size: 16px;
            box-sizing: border-box;
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
            width: 100%;
        }

        button:hover {
            background-color: #5C4011; /* Darker blue on hover */
        }

        .message {
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 4px;
            font-size: 16px;
            border: 1px solid #ddd; /* Border to match form inputs */
            background-color: #f9f9f9; /* Light gray background */
            color: #333; /* Dark text color for readability */
        }

        .content-container {
            padding: 20px;
            background-color: #ffffff; /* Light gray background */
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .error {
            color: red;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="form-container">
            <h3>Add New Customer</h3>

            <!-- Display success and error messages -->
            <div>
                <!-- Example error message -->
                <span id="error-message" class="error"></span>
            </div>

            <form id="customerForm" action="AddCustomerController" method="get">
                <div class="form-group">
                    <label for="firstname">First Name:</label>
                    <input type="text" id="firstname" name="firstname" pattern="[A-Za-z]+" title="First Name should contain only letters" required>
                </div>

                <div class="form-group">
                    <label for="lastname">Last Name:</label>
                    <input type="text" id="lastname" name="lastname" pattern="[A-Za-z]+" title="Last Name should contain only letters" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Id:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit">Add Customer</button>
            </form>
        </div>

        <!-- Additional Content Container -->
        <div class="content-container">
            <img src="https://png.pngtree.com/element_our/20200702/ourmid/pngtree-vector-cartoon-customer-service-png-free-material-image_2284307.jpg" alt="Customer Service">
        </div>
    </div>

    <script>
        document.getElementById('customerForm').addEventListener('submit', function(event) {
            var firstName = document.getElementById('firstname').value;
            var lastName = document.getElementById('lastname').value;
            var errorMessage = '';

            if (!/^[A-Za-z]+$/.test(firstName)) {
                errorMessage += 'First Name should contain only letters. ';
            }

            if (!/^[A-Za-z]+$/.test(lastName)) {
                errorMessage += 'Last Name should contain only letters. ';
            }

            if (errorMessage) {
                event.preventDefault(); 
                document.getElementById('error-message').textContent = errorMessage;
            }
        });
    </script>

</body>
</html>
