package com.cg.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.utility.DatabaseConnection;

public class TransactionDaoImpl implements TransactionDao {

	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();

	public CustomerDetails withdraw(CustomerDetails customerDetails) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer_details");
			int i = 0;
			while (rs.next()) {
				if (rs.getInt(1) == customerDetails.getAccountNo()) {
					double bal = customerDetails.getBalance();
					if (bal >= customerDetails.getAmount()) {
						bal -= customerDetails.getAmount();
						try {
							PreparedStatement stmt1 = connection
									.prepareStatement("update customer_details set balance = ? where account_no = ?");
							stmt1.setInt(2, customerDetails.getAccountNo());
							stmt1.setInt(1, (int) bal);
							stmt1.executeUpdate();
							customerDetails.setBalance(bal);
							i++;
							break;
						} catch (SQLException e) {
//							e.printStackTrace();
						}	
					}
					
				}
			}
			if (i != 1) {
				customerDetails.setFirstName(null);
			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerDetails;
	}

	public CustomerDetails deposit(CustomerDetails customerDetails) {
		double bal = customerDetails.getBalance();
		bal += customerDetails.getAmount();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update customer_details set balance = ? where account_no = ?");
			stmt.setInt(2, customerDetails.getAccountNo());
			stmt.setInt(1, (int) bal);
			stmt.executeUpdate();
			customerDetails.setBalance(bal);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerDetails;
	}

	public CustomerDetails showBalance(CustomerDetails customerDetails) {
		ResultSet resultSet;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select balance from customer_details where account_no = ?");
			preparedStatement.setInt(1,customerDetails.getAccountNo());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			customerDetails.setBalance(resultSet.getInt(10));
		} catch (SQLException e) {
		}
		return customerDetails;
	}

	public CustomerDetails fundTransfer(CustomerDetails customerDetails) {
		return null;
	}

}
