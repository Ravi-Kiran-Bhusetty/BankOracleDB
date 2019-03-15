package com.cg.banking.exceptions;

public class InvalidMobileNoException extends Exception{

	public InvalidMobileNoException() {
		System.err.println("Mobile number should be 10 digits");
	}
}
