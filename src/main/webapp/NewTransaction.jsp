<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 100%;
            max-width: 600px;
        }

        h1 {
            margin-top: 0;
            color: #333;
        }

        .button-group, .account-group {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-bottom: 20px;
        }

        .button-group button, .account-group button {
            background-color: #C4A484;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            flex: 1;
            min-width: 120px;
        }

        .button-group button.active, .account-group button.active {
            background-color: #5C4033;
        }

        .button-group button:hover, .account-group button:hover {
            background-color: #5C4033;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        input[type="number"], input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 15px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #28a745;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .hidden {
            display: none;
        }

        .error {
            color: #ff0000;
            margin-bottom: 20px;
        }

        .message {
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 4px;
            font-size: 16px;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
        }
        
    </style>
</head>
<body>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
    <div class="container">
        <!-- Display messages -->
        <h1>New Transaction</h1>
        <c:if test="${not empty successMessage}">
            <div class="message success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="message error">${errorMessage}</div>
        </c:if>
        <form id="transaction-form" action="NewTransactionController" method="post">
            <input type="hidden" id="account" name="accountId"/>
            <input type="hidden" id="transactionType" name="transactionType"/>
            <input type="hidden" id="transferAccountId" name="transferAccountId"/>

            <label>Select Account:</label>
            <div class="account-group">
                <c:forEach var="account" items="${accounts}">
                    <button type="button" data-value="${account.accountNumber}" data-balance="${account.balance}" class="account-button">${account.accountNumber} - ${account.accountType}</button>
                </c:forEach>
            </div>

            <label>Transaction Type:</label>
            <div class="button-group">
                <button type="button" data-value="credit" class="transaction-button">Credit</button>
                <button type="button" data-value="debit" class="transaction-button">Debit</button>
                <button type="button" data-value="transfer" class="transaction-button">Transfer</button>
            </div>

            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount" required/>

            <div id="transferAccount" class="hidden">
                <label for="transferAccountIdInput">Transfer To Account:</label>
                <input type="text" id="transferAccountIdInput" name="transferaccount_number"/>
            </div>

            <input type="submit" value="Submit"/>
        </form>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const transactionButtons = document.querySelectorAll('.transaction-button');
            const accountButtons = document.querySelectorAll('.account-button');
            const transferDiv = document.getElementById('transferAccount');
            const form = document.getElementById('transaction-form');
            let selectedAccountBalance = 0;

            transactionButtons.forEach(button => {
                button.addEventListener('click', function() {
                    transactionButtons.forEach(btn => btn.classList.remove('active'));
                    this.classList.add('active');
                    document.getElementById('transactionType').value = this.getAttribute('data-value');

                    if (this.getAttribute('data-value') === 'transfer') {
                        transferDiv.classList.remove('hidden');
                    } else {
                        transferDiv.classList.add('hidden');
                    }
                });
            });

            accountButtons.forEach(button => {
                button.addEventListener('click', function() {
                    accountButtons.forEach(btn => btn.classList.remove('active'));
                    this.classList.add('active');
                    document.getElementById('account').value = this.getAttribute('data-value');
                    selectedAccountBalance = parseFloat(this.getAttribute('data-balance'));
                });
            });

            form.addEventListener('submit', function(e) {
                const amountInput = document.getElementById('amount');
                const amount = parseFloat(amountInput.value);

                if (amount <= 0) {
                    e.preventDefault();
                    alert('Amount must be greater than zero.');
                    return;
                }

                if (document.querySelector('.transaction-button.active').getAttribute('data-value') === 'debit' && amount > selectedAccountBalance) {
                    e.preventDefault();
                    alert('Debit amount cannot be greater than the account balance.');
                }
            });
        });
    </script>
</body>
</html>
