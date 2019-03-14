package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;

public interface TransactionService {

	public CustomerDetails withdraw(CustomerDetails customerDetails);
	public CustomerDetails deposit(CustomerDetails customerDetails);
	public CustomerDetails showBalance(CustomerDetails customerDetails);
	public CustomerDetails fundTransfer(CustomerDetails customerDetails);
}
