package com.cg.banking.exceptions;

public class InvalidAadhaarNoException extends Exception{

	public InvalidAadhaarNoException() {
		System.err.println("Aadhaar No should be 12 in length");
	}
}
