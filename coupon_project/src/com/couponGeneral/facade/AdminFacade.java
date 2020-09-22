package com.couponGeneral.facade;

import java.util.List;

import com.couponGeneral.beans.Company;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.beans.Customer;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.OperationNotAllowedException;

public class AdminFacade extends ClientFacade {

	private String adminEmail = "admin@admin.com";
	private String adminPassword = "admin";

	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) {
		if (adminEmail.equals(email) && adminPassword.equals(password)) {
			System.out.println("login succsesfull!!");
			return true;
		}
		System.out.println("login failure");
		return false;
	}

	public void addCompany(Company company) throws AlreadyExistsException {
		List<Company> companies = companiesDAO.getAllCompanies();
		for (Company company2 : companies) {
			if (company.getName().equals(company2.getName()) && company.getEmail().equals(company2.getEmail())) {
				throw new AlreadyExistsException("company");
			}
		}
		companiesDAO.addCompany(company);
		System.out.println("company added successfully");
	}

	public void updateCompany(Company company) throws OperationNotAllowedException {
		List<Company> companies = companiesDAO.getAllCompanies();
		for (Company company2 : companies) {
			if (company.getId() == company2.getId() && company.getName().equals(company2.getName())) {
				companiesDAO.updateCompany(company);
				System.out.println("company updated successfully");
				return;
			}
		}
		throw new OperationNotAllowedException("changing name && id == ");
	}

	public void deleteCompany(int companyID) {
		List<Company> companies = companiesDAO.getAllCompanies();
		Coupon coup = new Coupon();
		for (Company company : companies) {
			if (company.getId() == companyID && companyID == coup.getCompanyId())
				;
			{
				couponsDAO.deleteCoupon(coup.getCompanyId());
				couponsDAO.deleteCouponCustomerVsCoupon(coup.getCompanyId());
				companiesDAO.deleteCompany(companyID);
			}
		}
	}

	public List<Company> getAllCompanies() {
		return companiesDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) {
	
		return companiesDAO.getOneCompany(companyID);
	}

	public void addCustomer(Customer customer) throws OperationNotAllowedException {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer custom : customers) {
			if (customer.getEmail().equals(custom.getEmail())) {
				throw new OperationNotAllowedException("cant add customer, email adress already in use - - -> ");
			}

		}
		customerDAO.addCustomer(customer);
		System.out.println("customer added successfully");
	}

	public void updateCustomer(Customer customer) throws OperationNotAllowedException {
		Customer cust = customerDAO.getOneCustomer(customer.getId());
		if (cust == null) {
			throw new OperationNotAllowedException("cant update customer id - - -> ");
		}

		if (cust.getId() == customer.getId()) {
			customerDAO.updateCustomer(customer);
			System.out.println("customer updated successfully");
		} else {
			throw new OperationNotAllowedException("cant update customer id - - -> ");
		}
	}

	public void deleteCustomer(int customerID) {
		List<Customer> customers = customerDAO.getAllCustomers();
		Coupon coupon = new Coupon();
		for (Customer custom : customers) {
			if (custom.getId() == customerID) {
				couponsDAO.deleteCouponCustomerVsCoupon(coupon.getId());
				couponsDAO.deleteCouponPurchase(customerID, coupon.getId());
				customerDAO.deleteCustomer(customerID);
			}
		}
	}

	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) {
		return customerDAO.getOneCustomer(customerID);
	}
}
