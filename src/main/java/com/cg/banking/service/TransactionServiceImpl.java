package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.dao.TransactionDao;
import com.cg.banking.dao.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService {

	CustomerDetails cd = new CustomerDetails();
	TransactionDao transactionDao = new TransactionDaoImpl();
	public CustomerDetails withdraw(CustomerDetails customerDetails) {
		
		return transactionDao.withdraw(customerDetails);
	}

	public CustomerDetails deposit(CustomerDetails customerDetails) {
		return transactionDao.deposit(customerDetails);
	}

	public CustomerDetails showBalance(CustomerDetails customerDetails) {
		return transactionDao.showBalance(customerDetails);
	}

	public CustomerDetails fundTransfer(CustomerDetails customerDetails) {
		return transactionDao.showBalance(customerDetails);
	}

}
