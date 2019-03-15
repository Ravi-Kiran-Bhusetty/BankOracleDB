package com.cg.banking.exceptions;

public class InvalidToAccountException extends Exception{

	public InvalidToAccountException() {
		System.err.println("Account doesn't exist to transfer fund");
	}
}
