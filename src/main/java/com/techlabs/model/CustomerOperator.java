package com.techlabs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techlabs.entity.Customer;
import com.techlabs.entity.User;

public class CustomerOperator extends BankDbConnection{
	
	
	public boolean AddCustomer(Customer customer) {
	PreparedStatement userPreparedStatement = null;
	PreparedStatement customerPreparedStatement = null;
	connectToDb();
	boolean isAdded=false;
	
	
			try {
				
				setPreparedStatement(getConnection().prepareStatement("insert into user (userName,userPassword,userType) values(?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS));
				
				getPreparedStatement().setString(1,customer.getFirstName());
				getPreparedStatement().setString(2, customer.getPassword());
				getPreparedStatement().setString(3, "customer");
				getPreparedStatement().executeUpdate();
				System.out.println("Record added successfully.");
				
				ResultSet userdb = getPreparedStatement().getGeneratedKeys();
				int userId=0;
				if(userdb.next()) {
					userId= userdb.getInt(1);
				}
				setPreparedStatement(getConnection().prepareStatement("insert into customer (userId,firstName,lastName,email,password) values(?,?,?,?,?)"));
				getPreparedStatement().setInt(1,userId);
				getPreparedStatement().setString(2,customer.getFirstName());
				getPreparedStatement().setString(3, customer.getLastName());
				getPreparedStatement().setString(4,customer.getEmail());
				getPreparedStatement().setString(5, customer.getPassword());
				
				getPreparedStatement().executeUpdate();
				System.out.println("Record added successfully.");
				
				 isAdded = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return isAdded;				
	}
	public List<Customer> getCustomers() {
		connectToDb();
		ResultSet dbCustomers = null;
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			dbCustomers =getStatement().executeQuery("select customerId,firstName, lastName, email from customer order by customerId desc;");
			while(dbCustomers.next()) {
				int customerId = dbCustomers.getInt("customerId");
                String firstName = dbCustomers.getString("firstName");
                String lastName = dbCustomers.getString("lastName");
                String email = dbCustomers.getString("email");
                
                Customer customer = new Customer(firstName, lastName, email,null);
                customer.setCustomerId(customerId);
                customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customers;
	}
	public boolean validatePassword(int userId, String currentPassword) {
		connectToDb();
		try {
			setPreparedStatement(getConnection().prepareStatement("select userPassword from user where userid=?;"));
			
			getPreparedStatement().setInt(1,userId );
			ResultSet resultSet = getPreparedStatement().executeQuery();
			
			if (resultSet.next()) {
                String storedPassword = resultSet.getString("userPassword");
                System.out.println(storedPassword);
                return storedPassword.equals(currentPassword);
                
                
            }
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	public void updatePassword(int userId, String newPassword) {
		connectToDb();
		
		
		try {
			setPreparedStatement(getConnection().prepareStatement("update user set userPassword=? where userid=?;"));
			getPreparedStatement().setString(1, newPassword );
			getPreparedStatement().setInt(2, userId);
			getPreparedStatement().executeUpdate();
			setPreparedStatement(getConnection().prepareStatement("update customer set password=? where userid=?;"));
			getPreparedStatement().setString(1, newPassword );
			getPreparedStatement().setInt(2, userId);
			getPreparedStatement().executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void updateCustomerProfile(int userId, String firstName, String lastName) {
	    connectToDb();
	    Connection connection = null;
	    PreparedStatement customerStatement = null;
	    PreparedStatement userStatement = null;
	    
	    try {
	        connection = getConnection();
	        
	        
	        String updateCustomerQuery = "UPDATE customer SET firstName = ?, lastName = ? WHERE userId = ?";
	        customerStatement = connection.prepareStatement(updateCustomerQuery);
	        customerStatement.setString(1, firstName);
	        customerStatement.setString(2, lastName);
	        customerStatement.setInt(3, userId);
	        customerStatement.executeUpdate();
	        
	        
	        String updateUserQuery = "UPDATE user SET userName = ? WHERE userId = ?";
	        userStatement = connection.prepareStatement(updateUserQuery);
	        userStatement.setString(1, firstName);
	        userStatement.setInt(2, userId);
	        userStatement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Clean up resources
	        try {
	            if (customerStatement != null) customerStatement.close();
	            if (userStatement != null) userStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public Customer getCustomerById(int customerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer customer = null;

        try {
            // Connect to the database
            connectToDb();
            conn = getConnection();
            
            // Prepare the SQL query to fetch customer details
            String sql = "SELECT c.customerId, c.firstName, c.lastName, c.email,c.userId, u.userPassword "
                       + "FROM customer c "
                       + "JOIN user u ON c.userId = u.userId "
                       + "WHERE c.customerId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            
            // Execute the query
            rs = stmt.executeQuery();
            
            // Process the result
            if (rs.next()) {
                int id = rs.getInt("customerId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("userPassword");
                int userId = rs.getInt("userId");
                
                // Create a Customer object and populate it with data from ResultSet
                customer = new Customer(firstName, lastName, email, password);
                customer.setCustomerId(id);
                customer.setUserId(userId);
                
                System.out.println("Fetching customer with ID: " + customerId);
                System.out.println("Fetched customerId from ResultSet: " + rs.getInt("customerId"));
                System.out.println("Fetched userId from ResultSet: " + rs.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return customer;
    }
	
	public boolean isCustomerExists(String email) {
	    connectToDb();
	    ResultSet rs = null;
	    try {
	        setPreparedStatement(getConnection().prepareStatement("SELECT email FROM customer WHERE email = ?"));
	        getPreparedStatement().setString(1, email);
	        rs = getPreparedStatement().executeQuery();
	        return rs.next(); // If email exists, rs.next() will return true
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	         // Assuming you have a method to close the connection
	    }
	    return false;
	}


}
