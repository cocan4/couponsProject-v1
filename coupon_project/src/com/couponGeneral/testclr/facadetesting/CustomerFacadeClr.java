package com.couponGeneral.testclr.facadetesting;

import java.sql.Date;

import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.NotExistsExeption;
import com.couponGeneral.exeptions.OperationNotAllowedException;
import com.couponGeneral.facade.CustomerFacade;
import com.couponGeneral.testclr.TestManager;
import com.couponGeneral.utils.ArtUtils;
import com.couponGeneral.utils.PrintMethod;

public class CustomerFacadeClr {

	public static void main(String[] args) {
		TestManager.testManager();
		CustomerFacade customerFacade = new CustomerFacade();
		System.out.println(ArtUtils.CUSTOMER_FACADE_ART);

		System.out.println("******************customer login*************************");
		customerFacade.login("ido@gmail.com", "12334");
		customerFacade.login("ido@gmail.com", "12334");
		
		System.out.println("******************customer bad login*************************");
		customerFacade.login("ido@gmail.com", "12354");

		System.out.println("******************add coupon purchase*************************");

		try {
			Coupon coupon = new Coupon(1, 1, Category.ELECTRICITY, "SOUND-SYSTEM", "bluetooth car audio",
					Date.valueOf("2020-09-01"), Date.valueOf("2020-09-30"), 10, 100, "sound");
			customerFacade.purchaseCoupon(coupon);
		} catch (AlreadyExistsException | OperationNotAllowedException | NotExistsExeption e1) {
			System.out.println(e1.getMessage());
		}
		
		System.out.println("******************add coupon purchase not working *************************");

		try {
			Coupon d4 = new Coupon(4,4, Category.ELECTRICITY, "SOUND-SYSTEM", "bluetooth car audio",
					Date.valueOf("2020-09-01"), Date.valueOf("2020-09-20"), 10, 100, "sound");
			customerFacade.purchaseCoupon(d4);
		} catch (AlreadyExistsException | OperationNotAllowedException | NotExistsExeption e1) {
			System.out.println(e1.getMessage());
		}

		try {
			Coupon d5 = new Coupon(5,3, Category.RESTAURANT, "HUMANGUS", "giant burgers house", Date.valueOf("2020-09-01"),
					Date.valueOf("2020-09-20"), 0, 100, "burgers");
			customerFacade.purchaseCoupon(d5);
		} catch (AlreadyExistsException | OperationNotAllowedException | NotExistsExeption e1) {
			System.out.println(e1.getMessage());
		}

	

		System.out.println("******************get customer coupons*************************");

		PrintMethod.print(customerFacade.getCustomerCoupons());

		System.out.println("******************get customer coupons by catagory*************************");

		PrintMethod.print(customerFacade.getCustomerCoupons(Category.ELECTRICITY));

		System.out.println("******************get customer coupons by max price*************************");

		PrintMethod.print(customerFacade.getCustomerCoupons(1000));

		System.out.println("******************get customer details*************************");
		PrintMethod.print(customerFacade.getCustomerDetalis());
	}

}
