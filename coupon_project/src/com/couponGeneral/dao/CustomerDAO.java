package com.couponGeneral.dao;

import java.util.List;

import com.couponGeneral.beans.Customer;

public interface CustomerDAO {

	boolean isCustomerExists(String email,String password);
	void addCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(int customerID);
	List<Customer>getAllCustomers();
	Customer getOneCustomer(int customerID);
	int getCustomerID(String email, String password);
}
