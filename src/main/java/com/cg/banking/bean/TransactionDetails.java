package com.cg.banking.bean;

public class TransactionDetails {

	private int transactionId, fromAccountNo, toAccountNo, amountTransferred;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(int fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public int getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(int toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public int getAmountTransferred() {
		return amountTransferred;
	}

	public void setAmountTransferred(int amountTransferred) {
		this.amountTransferred = amountTransferred;
	}
	
}