package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.dao.CustomerInformationDao;
import com.cg.banking.dao.CustomerInformationDaoImpl;
import com.cg.banking.exceptions.InvalidAadhaarNoException;
import com.cg.banking.exceptions.InvalidMobileNoException;
import com.cg.banking.exceptions.RegistrationFailedException;

public class CustomerInformationServiceImpl implements CustomerInformationService {

	CustomerInformationDao customerInformationDao = new CustomerInformationDaoImpl();

	public CustomerDetails login(CustomerDetails customerDetails) {
		return customerInformationDao.login(customerDetails);
	}

	public int customerRegistration(CustomerDetails customerDetails) {
		int accNo = -1;
		if (validateAadhaar(customerDetails.getAadhaarNo())) {
			if (validateMobile(customerDetails.getMobile_no())) {
				accNo = customerInformationDao.customerRegistration(customerDetails);
				if (accNo == -1) {
					try {
						throw new RegistrationFailedException();
					} catch (RegistrationFailedException e) {
					}
//					accNo = -1;
				}
			} else {
				try {
					throw new InvalidMobileNoException();
				} catch (InvalidMobileNoException e) {
				}
			}
		} else {
			try {
				throw new InvalidAadhaarNoException();
			} catch (InvalidAadhaarNoException e) {
			}
		}
		return accNo;
	}

	private boolean validateMobile(String mobile_no) {
		if (mobile_no.length() == 10)
			return true;
		else
			return false;
	}

	private boolean validateAadhaar(String aadhaarNo) {
		if (aadhaarNo.length() == 12)
			return true;
		else
			return false;
	}

}