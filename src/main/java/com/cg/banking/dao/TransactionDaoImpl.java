package com.cg.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.exceptions.InsufficientFundException;
import com.cg.banking.exceptions.InvalidToAccountException;
import com.cg.banking.utility.DatabaseConnection;

public class TransactionDaoImpl implements TransactionDao {

	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();

	public CustomerDetails withdraw(CustomerDetails customerDetails) {
		int i = 0;
		double bal = customerDetails.getBalance();
		if (bal >= customerDetails.getAmount()) {
			bal -= customerDetails.getAmount();
			try {
				PreparedStatement stmt1 = connection
						.prepareStatement("update customer_details set balance = ? where account_no = ?");
				stmt1.setInt(2, customerDetails.getAccountNo());
				stmt1.setInt(1, (int) bal);
				i = stmt1.executeUpdate();
				customerDetails.setBalance(bal);
			} catch (SQLException e) {
			}
		} else {
			try {
				throw new InsufficientFundException();
			} catch (InsufficientFundException e) {
			}
		}

		if (i == 1)
			return customerDetails;
		else
			return null;
	}

	public CustomerDetails deposit(CustomerDetails customerDetails) {
		double bal = customerDetails.getBalance();
		bal += customerDetails.getAmount();
		int i = 0;
		try {
			PreparedStatement stmt = connection
					.prepareStatement("update customer_details set balance = ? where account_no = ?");
			stmt.setInt(2, customerDetails.getAccountNo());
			stmt.setInt(1, (int) bal);
			i = stmt.executeUpdate();
			customerDetails.setBalance(bal);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 1)
			return customerDetails;
		else
			return null;
	}

	public CustomerDetails showBalance(CustomerDetails customerDetails) {
		ResultSet resultSet;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select balance from customer_details where account_no = ?");
			preparedStatement.setInt(1, customerDetails.getAccountNo());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			customerDetails.setBalance(resultSet.getInt(1));
		} catch (SQLException e) {
		}
		return customerDetails;
	}

	public CustomerDetails fundTransfer(CustomerDetails customerDetails) {
		int j = 0;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select account_no from customer_details");
			while (rs.next()) {
				if (rs.getInt(1) == customerDetails.getToAccountNo())
					j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (j == 1) {
			int i = 0;
			customerDetails = withdraw(customerDetails);
			if (customerDetails != null) {
				customerDetails = deposit(customerDetails);
				PreparedStatement preparedStatement;
				try {
					preparedStatement = connection.prepareStatement(
							"insert into transaction_details (transaction_id, from_account_no, to_account_no, amount_transferred) values(transaction_id_seq.nextval,?,?,?)");
					preparedStatement.setInt(1, customerDetails.getAccountNo());
					preparedStatement.setInt(2, customerDetails.getToAccountNo());
					preparedStatement.setInt(3, (int) customerDetails.getAmount());
					i = preparedStatement.executeUpdate();
				} catch (SQLException e) {
				}
			}
			if (i == 1)
				return customerDetails;
			else
				return null;
		} else {
			try {
				throw new InvalidToAccountException();
			} catch (InvalidToAccountException e) {
			}
			return null;
		}

	}

}