package com.cg.banking.exceptions;

public class RegistrationFailedException extends Exception{

	public RegistrationFailedException() {
		System.err.println("Cannot Register. Customer Already Exists");
	}
}
