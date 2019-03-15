package com.cg.banking.exceptions;

public class InsufficientFundException extends Exception{

	public InsufficientFundException() {
		System.err.println("Account Balance Insufficient to Withdraw");
	}
}
