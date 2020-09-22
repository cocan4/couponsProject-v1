package com.couponGeneral.testclr.dbdaotesting;

import com.couponGeneral.beans.Customer;
import com.couponGeneral.db.DataBaseManager;
import com.couponGeneral.dbdao.CustomerDBDAO;
import com.couponGeneral.utils.ArtUtils;
import com.couponGeneral.utils.PrintMethod;

public class CustomerTesting {

	public static void main(String[] args) {
		
		DataBaseManager.createDropTables();
		System.out.println(ArtUtils.CUSTOMER_DBDAO_ART);
		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		
		System.out.println("***********ADD_CUSTOMER_DBDAO*************");
		Customer a1 = new Customer("ido", "shay", "ido@gmail.com", "12334");
		Customer a2 = new Customer("moran", "shay", "morani@gmail.com", "12234");
		Customer a3 = new Customer("ziv", "shay", "zivi@gmail.com", "13234");

		customerDBDAO.addCustomer(a1);
		customerDBDAO.addCustomer(a2);
		customerDBDAO.addCustomer(a3);
		PrintMethod.print(customerDBDAO.getAllCustomers());
		
		System.out.println("***********GET_ALL_CUSTOMER_DBDAO*************");
		PrintMethod.print(customerDBDAO.getAllCustomers());
		
		System.out.println("***********UPDATE_CUSTOMER_DBDAO*************");
		a1.setEmail("shasha@gmail.com");
		customerDBDAO.updateCustomer(a1);
		PrintMethod.print(customerDBDAO.getAllCustomers());

		System.out.println("***********DELETE_CUSTOMER_DBDAO*************");
		customerDBDAO.deleteCustomer(a3.getId());
		PrintMethod.print(customerDBDAO.getAllCustomers());

		System.out.println("***********GET_ONE_CUSTOMER_DBDAO*************");
		PrintMethod.print(customerDBDAO.getOneCustomer(2));

	}

}
