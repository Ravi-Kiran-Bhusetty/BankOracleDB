package com.cg.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.utility.DatabaseConnection;

public class CustomerInformationDaoImpl implements CustomerInformationDao {

//	Scanner sc = new Scanner(System.in);
	DatabaseConnection db = new DatabaseConnection();
	Connection connection = db.database();
	
	public CustomerDetails login(CustomerDetails customerDetails) {
		int i = 0;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer_details");
			while (rs.next()) {
				if (rs.getInt(1) == customerDetails.getAccountNo() && rs.getString(5).equals(customerDetails.getPassword())) {
					customerDetails.setBalance(rs.getInt(10));
					customerDetails.setFirstName(rs.getString(2));
					i++;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i == 1)
		return customerDetails;
		else
			return null;
	}

	public int customerRegistration(CustomerDetails customerDetails) {
		int i = -1, accNo = 0;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("insert into customer_details (account_no, first_name, last_name, email_id, password, pancard_no, aadhaar_no, address, mobile_no) values(customer_account_seq.nextval,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, customerDetails.getFirstName());
			preparedStatement.setString(2, customerDetails.getLastName());
			preparedStatement.setString(3, customerDetails.getEmailId());
			preparedStatement.setString(4, customerDetails.getPassword());
			preparedStatement.setString(5, customerDetails.getPanCardNo());
			preparedStatement.setString(6, customerDetails.getAadhaarNo());
			preparedStatement.setString(7, customerDetails.getAddress());
			preparedStatement.setString(8, customerDetails.getMobile_no());
			i = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
		}
		if(i == 1) {
			try {
				preparedStatement = connection.prepareStatement("select account_no from customer_details where aadhaar_no = ?");
				preparedStatement.setString(1,customerDetails.getAadhaarNo());
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				accNo = resultSet.getInt(1);
			} catch (SQLException e) {
			}
			return accNo;
		}	
		else
			return -1;

	}

}