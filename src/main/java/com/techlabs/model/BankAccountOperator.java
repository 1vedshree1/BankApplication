package com.techlabs.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.entity.BankAccount;
import com.techlabs.entity.Customer;

public class BankAccountOperator extends BankDbConnection{
	public boolean addBankAccount(BankAccount bank) {
		
		connectToDb();
		boolean isAdded=false;
		
				try {
					
					
					setPreparedStatement(getConnection().prepareStatement("insert into account (customerId,accountNumber,accountType,balance) values(?,?,?,?)"));
					getPreparedStatement().setInt(1,bank.getCustomerId());
					getPreparedStatement().setLong(2,bank.getAccountNumber());
					getPreparedStatement().setString(3, bank.getAccountType());
					getPreparedStatement().setDouble(4,bank.getBalance());
					
					
					getPreparedStatement().executeUpdate();
					System.out.println("Record added successfully.");
					
					 isAdded = true;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return isAdded;				
		}
	public List<BankAccount> getAccounts(int customerId) {
	    connectToDb();
	    List<BankAccount> accounts = new ArrayList<>();
	    ResultSet dbAccounts = null;
	    
	    try {
	        setPreparedStatement(getConnection().prepareStatement("SELECT accountNumber, accountType, balance FROM account WHERE customerId = ?;"));
	        getPreparedStatement().setInt(1, customerId);
	        dbAccounts = getPreparedStatement().executeQuery();
	        
	        while (dbAccounts.next()) {
	            long accountNumber = dbAccounts.getLong("accountNumber");
	            String accountType = dbAccounts.getString("accountType");
	            double balance = dbAccounts.getDouble("balance");
	            
	            BankAccount account = new BankAccount(customerId, accountNumber, accountType, balance);
	            accounts.add(account);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        try {
	            if (dbAccounts != null) dbAccounts.close();
	            if (getPreparedStatement() != null) getPreparedStatement().close();
	            if (getConnection() != null) getConnection().close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return accounts;
	}
	
	public List<BankAccount> getOtherAccounts(int customerId) {
	    connectToDb();
	    List<BankAccount> otherAccounts = new ArrayList<>();
	    ResultSet dbAccounts = null;
	    
	    try {
	        
	        setPreparedStatement(getConnection().prepareStatement("SELECT accountId,accountNumber, accountType, balance FROM account WHERE customerId <> ?;"));
	        getPreparedStatement().setInt(1, customerId);
	        dbAccounts = getPreparedStatement().executeQuery();
	        
	        while (dbAccounts.next()) {
	        	int accountId = dbAccounts.getInt("accountId");
	            long accountNumber = dbAccounts.getLong("accountNumber");
	            String accountType = dbAccounts.getString("accountType");
	            double balance = dbAccounts.getDouble("balance");
	            
	           
	            BankAccount account = new BankAccount(customerId, accountNumber, accountType, balance);
	           
	            otherAccounts.add(account);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        try {
	            if (dbAccounts != null) dbAccounts.close();
	            if (getPreparedStatement() != null) getPreparedStatement().close();
	            if (getConnection() != null) getConnection().close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return otherAccounts;
	}
	public BankAccount getAccountByNumber(long selectedAccountNumber) {
	    connectToDb();
	    BankAccount account = null; 
	    
	    try {
	        
	        setPreparedStatement(getConnection().prepareStatement("SELECT * FROM account WHERE accountNumber = ?"));
	        getPreparedStatement().setLong(1, selectedAccountNumber);
	        
	        
	        ResultSet rs = getPreparedStatement().executeQuery();
	        
	        
	        if (rs.next()) {
	        	int accountId = rs.getInt("accountId");
	            int customerId = rs.getInt("customerId");
	            long accountNumber = rs.getLong("accountNumber");
	            String type = rs.getString("accountType");
	            double balance = rs.getDouble("balance");
	            
	            
	            account = new BankAccount(customerId, accountNumber, type, balance);
	            account.setAccountId(accountId);
	            System.out.println("Query executed successfully.");
	            System.out.println("Customer ID: " + customerId + ", Account Number: " + accountNumber + ", Type: " + type + ", Balance: " + balance);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        try {
	            if (getPreparedStatement() != null) getPreparedStatement().close();
	            if (getConnection() != null) getConnection().close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	   
	    return account;
	}
	public int getAccountId(long selectedAccountNumber) {
	    connectToDb();
	    int accountId = -1; 

	    try {
	       
	        setPreparedStatement(getConnection().prepareStatement("SELECT accountId FROM account WHERE accountNumber = ?"));
	        getPreparedStatement().setLong(1, selectedAccountNumber);

	        
	        ResultSet rs = getPreparedStatement().executeQuery();

	        if (rs.next()) {
	            accountId = rs.getInt("accountId"); // Assign the value to accountId
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        try {
	            if (getPreparedStatement() != null) getPreparedStatement().close();
	            if (getConnection() != null) getConnection().close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return accountId; // Return the retrieved accountId
	}


	public void creditAccount(BankAccount account, double amount) {
       
        account.setBalance(account.getBalance() + amount);
        
        updateAccount(account);
    }

    public void debitAccount(BankAccount account, double amount) {
        
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            
            updateAccount(account);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public void transferFunds(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount.getBalance() >= amount) {
            
            debitAccount(fromAccount, amount);
           
            toAccount.setBalance(toAccount.getBalance() + amount);
            
            updateAccount(toAccount);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    private void updateAccount(BankAccount account) {
    	connectToDb();
    	try {
			setPreparedStatement(getConnection().prepareStatement("UPDATE account SET balance = ? WHERE accountNumber = ?;"));
			
			getPreparedStatement().setDouble(1, account.getBalance());
			getPreparedStatement().setDouble(2, account.getAccountNumber());
			getPreparedStatement().executeUpdate();
			
			System.out.println("Record updated successfully.");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
    
    public void saveTransaction(int accountId, String transactionType, double amount, long transferAccountNumber) {
    	connectToDb();
		
				try {
					
					
					setPreparedStatement(getConnection().prepareStatement("INSERT INTO transactions (accountId, transaction_type, amount, transfer_accountNumber) VALUES (?, ?, ?, ?)"));
					getPreparedStatement().setInt(1,accountId);
					getPreparedStatement().setString(2,transactionType);
					getPreparedStatement().setDouble(3,amount);
					getPreparedStatement().setLong(4,transferAccountNumber);
					
					
					getPreparedStatement().executeUpdate();
					System.out.println("Record added successfully.");
					
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
						
    	
    	
    }

    

		
	
}
