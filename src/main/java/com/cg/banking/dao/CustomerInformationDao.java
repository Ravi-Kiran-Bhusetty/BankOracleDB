package com.cg.banking.dao;

import com.cg.banking.bean.CustomerDetails;

public interface CustomerInformationDao {

	public CustomerDetails login(CustomerDetails customerDetails);
	public int customerRegistration(CustomerDetails customerDetails);
}

