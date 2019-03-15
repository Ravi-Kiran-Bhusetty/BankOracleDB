package com.cg.banking.exceptions;

public class InvalidAmountException extends Exception{

	public InvalidAmountException() {
		System.err.println("Amount cannot be less than zero");
	}
}
