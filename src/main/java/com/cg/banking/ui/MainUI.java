package com.cg.banking.ui;

import java.util.Scanner;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.service.CustomerInformationService;
import com.cg.banking.service.CustomerInformationServiceImpl;
import com.cg.banking.service.TransactionService;
import com.cg.banking.service.TransactionServiceImpl;

public class MainUI {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomerInformationService customerInformationService = new CustomerInformationServiceImpl();
		TransactionService transactionService = new TransactionServiceImpl();
		CustomerDetails customerDetails = new CustomerDetails();
		CustomerDetails customerDetails1 = new CustomerDetails();
		do {
			System.out.println("Menu\n1. Registration\n2. Login");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				System.out.println("Register a new password");
				customerDetails.setPassword(sc.next());
				System.out.println("Enter first name and last name");
				customerDetails.setFirstName(sc.next());
				customerDetails.setLastName(sc.next());
				System.out.println("Enter email id");
				customerDetails.setEmailId(sc.next());
				System.out.println("Enter aadhaar card number");
				customerDetails.setAadhaarNo(sc.next());
				System.out.println("Enter pan card number");
				customerDetails.setPanCardNo(sc.next());
				System.out.println("Enter mobile number");
				customerDetails.setMobile_no(sc.next());
				System.out.println("Enter the address");
				customerDetails.setAddress(sc.next());
				int accNo = customerInformationService.customerRegistration(customerDetails);
				System.out.println("Registration Successful. Account no: " + accNo);
				System.out.println("Redirecting to login");
				// break;

			case 2:
				System.out.println("Enter account number");
				customerDetails.setAccountNo(sc.nextInt());
				System.out.println("Enter password");
				customerDetails.setPassword(sc.next());
				customerDetails1 = customerInformationService.login(customerDetails);
				if (customerDetails1.getFirstName() != null) {
					System.out.println("Welcome " + customerDetails1.getFirstName());
					do {
						System.out.println(
								"Choose any operation\n1. Withdraw\n2. Deposit\n3. Balance Enquiry\n4. Fund Transfer\n5. Exit");
						int key = sc.nextInt();
						switch (key) {
						case 1:
//							System.err.println(customerDetails1.getAccountNo());
							System.out.println("Enter withdraw amount");							
							customerDetails1.setAmount(sc.nextDouble());
							customerDetails1 = transactionService.withdraw(customerDetails1);
							System.out.println("Remaining balance: "+customerDetails1.getBalance());
							break;

						case 2:
							System.out.println("Enter deposit amount");
							customerDetails1.setAmount(sc.nextDouble());
							customerDetails1 = transactionService.deposit(customerDetails1);
							System.out.println("Remaining balance: "+customerDetails1.getBalance());
							break;

						case 3:
							customerDetails1 = transactionService.showBalance(customerDetails1);
							System.out.println("Your balance is: "+customerDetails1.getBalance());
							break;

						case 4:
							System.out.println("Enter the fund amount to transfer");
							customerDetails1.setAmount(sc.nextDouble());
							System.out.println("Enter the account number to which fund has to be transferred");
							int acc = sc.nextInt();
							int previousAccount = customerDetails1.getAccountNo();
							customerDetails1 = transactionService.withdraw(customerDetails1);
							customerDetails1.setAccountNo(acc);
							customerDetails1 = transactionService.deposit(customerDetails1);
							customerDetails1.setAccountNo(previousAccount);
							break;

						case 5:
							System.exit(0);
						default:
							System.err.println("Invalid Choice");
							break;
						}
					} while (true);
				} else
					System.out.println("Account number or password incorrect");
				break;

			default:
				System.err.println("Invalid Choice");
				break;
			}

		} while (true);
	}

}
