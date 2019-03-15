package com.cg.banking.dao;

import com.cg.banking.bean.CustomerDetails;

import junit.framework.TestCase;

public class TransactionDaoImplTest extends TestCase {

	static TransactionDao transactionDao;
	static CustomerDetails customerDetails;
	
	@BeforeAll
	public void init() {
		transactionDao = new TransactionDaoImpl();
		customerDetails = new CustomerDetails();
	}
	@Test
	public void testWithdraw() {
		customerDetails.setAccountNo(10001);
		customerDetails.setBalance(245);
		customerDetails.setAmount(120);
		customerDetails = transactionDao.withdraw(customerDetails);
		assertEquals(125, customerDetails.getBalance());
	}
	@Test
	public void testDeposit() {
		customerDetails.setAccountNo(10001);
		customerDetails.setBalance(245);
		customerDetails.setAmount(120);
		customerDetails = transactionDao.deposit(customerDetails);
		assertEquals(365, customerDetails.getBalance());
	}
	@Test
	public void testShowBalance() {
		customerDetails.setAccountNo(10001);
		customerDetails = transactionDao.showBalance(customerDetails);
		assertEquals(365, customerDetails.getBalance());
	}
	@Test
	public void testFundTransfer() {
		customerDetails.setAccountNo(10001);
		customerDetails.setToAccountNo(10002);
		customerDetails.setAmount(120);
		customerDetails = transactionDao.fundTransfer(customerDetails);
		assertEquals(null, customerDetails);
	}

}
