package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;

public interface CustomerInformationService {

	public CustomerDetails login(CustomerDetails customerDetails);
	public int customerRegistration(CustomerDetails customerDetails);
}

