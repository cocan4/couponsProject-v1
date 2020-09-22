package com.couponGeneral.testclr.facadetesting;

import com.couponGeneral.beans.Company;
import com.couponGeneral.beans.Customer;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.OperationNotAllowedException;
import com.couponGeneral.facade.AdminFacade;
import com.couponGeneral.testclr.TestManager;
import com.couponGeneral.utils.PrintMethod;

public class AdminFacadeClr {

	public static void main(String[] args) {
		TestManager.testManager();
		AdminFacade adminFacade = new AdminFacade();

		System.out.println("******************admin login*************************");
		adminFacade.login("admin@admin.com", "admin");
		System.out.println("******************admin bad login*************************");
		adminFacade.login("admin1@admin.com", "admin");

		System.out.println("******************add company*************************");
		Company c5 = new Company("spallding", "spallding@gmail.com", "112233");
		Company c6 = new Company("humangus burgers", "hugeburgers@gmail.com", "112233");
		Company c7 = new Company("fatal vacations", "fatal@gmail.com", "112233");
		Company c8 = new Company("toys we love", "toys@gmail.com", "112233");
		Company c9 = new Company("easy-cell", "ecell@gmail.com", "112233");
		
		try {
			adminFacade.addCompany(c5);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCompany(c6);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCompany(c7);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCompany(c8);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCompany(c9);
		} catch (AlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		PrintMethod.print(adminFacade.getAllCompanies());

		System.out.println("******************update company*************************");
		c5.setId(5);
		c5.setPassword("1234");
		try {
			adminFacade.updateCompany(c5);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		PrintMethod.print(adminFacade.getAllCompanies());

		System.out.println("******************update company - not working*************************");
		//admin can't update id
		c5.setId(3);
		try {
			adminFacade.updateCompany(c5);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		//admin can't update name of company
		c6.setName("gumngos");
		try {
			adminFacade.updateCompany(c6);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("******************delete company*************************");
		adminFacade.deleteCompany(6);
		PrintMethod.print(adminFacade.getAllCompanies());

		System.out.println("******************get all companies*************************");
		PrintMethod.print(adminFacade.getAllCompanies());

		System.out.println("******************add new customer*************************");
		Customer a4 = new Customer(4,"ron", "shay", "ronchi@gmail.com", "112233");
		Customer a5 = new Customer(5,"noam", "shay", "numchak@gmail.com", "112233");
		Customer a6 = new Customer(6,"shira", "shay", "chikas@gmail.com", "112233");
		try {
			adminFacade.addCustomer(a4);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCustomer(a5);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		try {
			adminFacade.addCustomer(a6);
			PrintMethod.print(adminFacade.getAllCustomers());
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("*********************add new customer not working*************************");
		//admin can't add existing customer
		try {
			adminFacade.addCustomer(a5);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("************************update customer*********************************");
		a4.setLastName("shai");
		a4.setPassWord("1234");
		try {
			adminFacade.updateCustomer(a4);
			
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("******************update customer not working****************************");
		//admin can't change id
		a4.setId(99);
		try {
			adminFacade.updateCustomer(a4);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("******************delete customer****************************");
		adminFacade.deleteCustomer(6);
		PrintMethod.print(adminFacade.getAllCustomers());
		
		System.out.println("******************get one customer****************************");
		PrintMethod.print(adminFacade.getOneCustomer(5));
		
		System.out.println("******************get all customers****************************");
		PrintMethod.print(adminFacade.getAllCustomers());
	}
	

}
