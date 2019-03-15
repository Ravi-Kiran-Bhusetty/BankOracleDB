package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.dao.TransactionDao;
import com.cg.banking.dao.TransactionDaoImpl;
import com.cg.banking.exceptions.InvalidAmountException;

public class TransactionServiceImpl implements TransactionService {

	CustomerDetails cd = new CustomerDetails();
	TransactionDao transactionDao = new TransactionDaoImpl();

	public CustomerDetails withdraw(CustomerDetails customerDetails) {
		if (customerDetails.getAmount() > 0) {
			cd = transactionDao.withdraw(customerDetails);
			if (cd != null)
				return transactionDao.withdraw(customerDetails);
			else
				return null;
		} else {
			try {
				throw new InvalidAmountException();
			} catch (InvalidAmountException e) {
			}
			return null;
		}
	}

	public CustomerDetails deposit(CustomerDetails customerDetails) {
		if (customerDetails.getAmount() > 0)
			return transactionDao.deposit(customerDetails);
		else {
			try {
				throw new InvalidAmountException();
			} catch (InvalidAmountException e) {
			}
			return null;
		}
	}

	public CustomerDetails showBalance(CustomerDetails customerDetails) {
		return transactionDao.showBalance(customerDetails);
	}

	public CustomerDetails fundTransfer(CustomerDetails customerDetails) {
		if (customerDetails.getAmount() > 0)
			return transactionDao.fundTransfer(customerDetails);
		else {
			try {
				throw new InvalidAmountException();
			} catch (InvalidAmountException e) {
			}
			return null;
		}
	}

}