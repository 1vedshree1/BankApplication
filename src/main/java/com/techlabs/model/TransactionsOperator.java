package com.techlabs.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.entity.Transaction;

public class TransactionsOperator extends BankDbConnection {

    public List<Transaction> getTransactions() {
        connectToDb();
        ResultSet dbTransactions = null;
        List<Transaction> transactions = new ArrayList<>();

        try {
            dbTransactions = getStatement().executeQuery(
                "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                "FROM transactions t " +
                "JOIN account a ON t.accountId = a.accountId " +
                "ORDER BY t.transactionId"
            );
            while (dbTransactions.next()) {
                transactions.add(new Transaction(
                    dbTransactions.getInt(1),
                    dbTransactions.getString(2),
                    dbTransactions.getString(3),
                    dbTransactions.getDouble(4),
                    dbTransactions.getLong(5),
                    dbTransactions.getLong(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(dbTransactions, null);
        }

        return transactions;
    }

    public List<Transaction> getTransactionsByType(String type) {
        connectToDb();
        ResultSet dbTransactions = null;
        List<Transaction> transactions = new ArrayList<>();
        String query;

        try {
            if ("debit".equalsIgnoreCase(type)) {
                
                query = "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                        "FROM transactions t " +
                        "JOIN account a ON t.accountId = a.accountId " +
                        "WHERE t.transaction_type IN ('debit', 'transfer') " +
                        "ORDER BY t.transactionId";
                setPreparedStatement(getConnection().prepareStatement(query));
            } else {
                
                query = "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                        "FROM transactions t " +
                        "JOIN account a ON t.accountId = a.accountId " +
                        "WHERE t.transaction_type = ? " +
                        "ORDER BY t.transactionId";
                setPreparedStatement(getConnection().prepareStatement(query));
                getPreparedStatement().setString(1, type);
            }

            dbTransactions = getPreparedStatement().executeQuery();
            while (dbTransactions.next()) {
                transactions.add(new Transaction(
                    dbTransactions.getInt(1),
                    dbTransactions.getString(2),
                    dbTransactions.getString(3),
                    dbTransactions.getDouble(4),
                    dbTransactions.getLong(5),
                    dbTransactions.getLong(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(dbTransactions, getPreparedStatement());
        }

        return transactions;
    }

    public List<Transaction> getTransactionsbyId() {
        connectToDb();
        ResultSet dbTransactions = null;
        List<Transaction> transactions = new ArrayList<>();

        try {
            dbTransactions = getStatement().executeQuery(
                "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                "FROM transactions t " +
                "JOIN account a ON t.accountId = a.accountId"
            );
            while (dbTransactions.next()) {
                transactions.add(new Transaction(
                    dbTransactions.getInt(1),
                    dbTransactions.getString(2),
                    dbTransactions.getString(3),
                    dbTransactions.getDouble(4),
                    dbTransactions.getLong(5),
                    dbTransactions.getLong(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(dbTransactions, null);
        }

        return transactions;
    }

    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        connectToDb();
        ResultSet dbTransactions = null;
        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                       "FROM transactions t " +
                       "JOIN account a ON t.accountId = a.accountId " +
                       "JOIN customer c ON a.customerId = c.customerId " +
                       "WHERE c.customerId = ? " +
                       "ORDER BY t.transactionId ASC";

        try {
            setPreparedStatement(getConnection().prepareStatement(query));
            getPreparedStatement().setInt(1, customerId);
            dbTransactions = getPreparedStatement().executeQuery();
            while (dbTransactions.next()) {
                transactions.add(new Transaction(
                    dbTransactions.getInt(1),
                    dbTransactions.getString(2),
                    dbTransactions.getString(3),
                    dbTransactions.getDouble(4),
                    dbTransactions.getLong(5),
                    dbTransactions.getLong(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(dbTransactions, getPreparedStatement());
        }

        return transactions;
    }

    public List<Transaction> getFilteredTransactionsByCustomerId(int customerId, String type) {
        connectToDb();
        ResultSet dbTransactions = null;
        List<Transaction> transactions = new ArrayList<>();
        String query;

        try {
            if ("debit".equalsIgnoreCase(type)) {
                
                query = "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                        "FROM transactions t " +
                        "JOIN account a ON t.accountId = a.accountId " +
                        "JOIN customer c ON a.customerId = c.customerId " +
                        "WHERE c.customerId = ? AND t.transaction_type IN ('debit', 'transfer') " +
                        "ORDER BY t.transactionId ASC";
                setPreparedStatement(getConnection().prepareStatement(query));
                getPreparedStatement().setInt(1, customerId);
            } else {
               
                query = "SELECT t.transactionId, t.transactionDate, t.transaction_type, t.amount, a.accountNumber, t.transfer_accountNumber " +
                        "FROM transactions t " +
                        "JOIN account a ON t.accountId = a.accountId " +
                        "JOIN customer c ON a.customerId = c.customerId " +
                        "WHERE c.customerId = ? AND t.transaction_type = ? " +
                        "ORDER BY t.transactionId ASC";
                setPreparedStatement(getConnection().prepareStatement(query));
                getPreparedStatement().setInt(1, customerId);
                getPreparedStatement().setString(2, type);
            }

            dbTransactions = getPreparedStatement().executeQuery();
            while (dbTransactions.next()) {
                transactions.add(new Transaction(
                    dbTransactions.getInt(1),
                    dbTransactions.getString(2),
                    dbTransactions.getString(3),
                    dbTransactions.getDouble(4),
                    dbTransactions.getLong(5),
                    dbTransactions.getLong(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(dbTransactions, getPreparedStatement());
        }

        return transactions;
    }

    // Helper method to close resources
    private void closeResources(ResultSet rs, java.sql.Statement stmt) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
