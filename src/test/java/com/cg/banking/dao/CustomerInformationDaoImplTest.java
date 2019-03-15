package com.cg.banking.dao;

import com.cg.banking.bean.CustomerDetails;

import junit.framework.TestCase;

public class CustomerInformationDaoImplTest extends TestCase {

	static CustomerInformationDao customerInformationDao;
	static CustomerDetails cd;
	@BeforeAll
	public void init() {
		customerInformationDao = new CustomerInformationDaoImpl();
		cd = new CustomerDetails();
	}
	@Test
	public void testLogin() {
//		CustomerDetails cd = new CustomerDetails();
//		CustomerInformationDao customerInformationDao = new CustomerInformationDaoImpl();
		cd.setAccountNo(10001);
		cd.setPassword("1234");
		assertEquals(null, customerInformationDao.login(cd));
	}
	@Test
	public void testCustomerRegistration() {
		cd.setFirstName("Ravi");
		cd.setLastName("Kiran");
		cd.setEmailId("ravi@kiran");
		cd.setAadhaarNo("999999999999");
		cd.setAddress("HYD");
		cd.setMobile_no("0000000000");
		cd.setPanCardNo("dsgfd");
		cd.setPassword("1111");
		assertEquals(10001, customerInformationDao.customerRegistration(cd));
	}

}
