package com.cg.banking.service;

import com.cg.banking.bean.CustomerDetails;
import com.cg.banking.dao.CustomerInformationDao;
import com.cg.banking.dao.CustomerInformationDaoImpl;

public class CustomerInformationServiceImpl implements CustomerInformationService {

	CustomerInformationDao customerInformationDao = new CustomerInformationDaoImpl();

	public CustomerDetails login(CustomerDetails customerDetails) {
		return customerInformationDao.login(customerDetails);
	}

	public int customerRegistration(CustomerDetails customerDetails) {
		int accNo = customerInformationDao.customerRegistration(customerDetails);
		if (accNo != -1)
			return accNo;
		else
			return -1;
	}

}
