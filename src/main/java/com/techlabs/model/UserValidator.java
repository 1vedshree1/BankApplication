package com.techlabs.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidator extends BankDbConnection {

	public ResultSet getAllUsers() {

		connectToDb();
		ResultSet dbUsers = null;
		try {
			dbUsers = getStatement().executeQuery("select * from user;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dbUsers;

	}

	public boolean validateUser(String username, String password, String type) {
		int userId;
		ResultSet dbUsers = getAllUsers();

		try {
			while (dbUsers.next()) {
				if (dbUsers.getString("userName").equals(username))
					if (dbUsers.getString("userPassword").equals(password))
						if (dbUsers.getString("userType").equals(type))

							return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public int getUserId(String userName, String userPassword) {
		int userId = 0;
		try {
			setPreparedStatement(
					getConnection().prepareStatement("SELECT userId FROM user where userName=? and userPassword=?;"));
			getPreparedStatement().setString(1, userName);
			getPreparedStatement().setString(2, userPassword);
			ResultSet rs = getPreparedStatement().executeQuery();

			if (rs.next()) {
				userId = rs.getInt("userId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	public int getCustomerIdByUserId(int userId) {
		int customerId = 0;
		try {
			setPreparedStatement(getConnection().prepareStatement("SELECT customerId FROM customer WHERE userId = ?;"));
			getPreparedStatement().setInt(1, userId);
			ResultSet rs = getPreparedStatement().executeQuery();

			if (rs.next()) {
				customerId = rs.getInt("customerId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerId;
	}

}
