package com.cg.banking.dao;

import com.cg.banking.bean.CustomerDetails;

public interface TransactionDao {

	public CustomerDetails withdraw(CustomerDetails customerDetails);
	public CustomerDetails deposit(CustomerDetails customerDetails);
	public CustomerDetails showBalance(CustomerDetails customerDetails);
	public CustomerDetails fundTransfer(CustomerDetails customerDetails);
}
