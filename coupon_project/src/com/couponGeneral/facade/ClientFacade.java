package com.couponGeneral.facade;

import com.couponGeneral.dbdao.CompaniesDBDAO;
import com.couponGeneral.dao.CompaniesDAO;
import com.couponGeneral.dao.CouponsDAO;
import com.couponGeneral.dao.CustomerDAO;
import com.couponGeneral.dbdao.CouponsDBDAO;
import com.couponGeneral.dbdao.CustomerDBDAO;

	public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO;
	protected CustomerDAO customerDAO;
	protected CouponsDAO couponsDAO;

	public ClientFacade() {
		this.companiesDAO = new CompaniesDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponsDAO = new CouponsDBDAO();
	}

	public abstract boolean login(String email, String password);

}
