package com.couponGeneral.testclr.facadetesting;

import java.sql.Date;

import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.OperationNotAllowedException;
import com.couponGeneral.facade.CompaniesFacade;
import com.couponGeneral.testclr.TestManager;
import com.couponGeneral.utils.ArtUtils;
import com.couponGeneral.utils.PrintMethod;

public class CompanyFacadeClr {

	public static void main(String[] args) {

		TestManager.testManager();
		CompaniesFacade companiesFacade = new CompaniesFacade();

		System.out.println(ArtUtils.COMPANY_FACADE_ART);
		System.out.println("******************COMPANY FACADE TESTING*************************");

		companiesFacade.login("nike@gmail.com", "122335");
		companiesFacade.login("nike@gmail.com", "12233");

		System.out.println("******************add coupon*************************");

		Coupon d1 = new Coupon(3, Category.SHOOES, "Nike Runnig Shooes", "Air Flight", Date.valueOf("2020-09-01"),
				Date.valueOf("2020-09-30"), 10, 550, "shoes");
		Coupon d2 = new Coupon(3, Category.CLOTHING, "Nike Runnig T-shirts", "DRY-FIT SHIRT",
				Date.valueOf("2020-09-01"), Date.valueOf("2020-09-30"), 10, 550, "T-SHIRTS");

			try {
				companiesFacade.addCoupon(d1);
			} catch (AlreadyExistsException e2) {
				System.out.println(e2.getMessage());
			}
			try {
				companiesFacade.addCoupon(d2);
			} catch (AlreadyExistsException e2) {
				System.out.println(e2.getMessage());
			}
				PrintMethod.print(companiesFacade.getCompanyCoupons());
		

		System.out.println("******************add coupon not working*************************");
		try {
			companiesFacade.addCoupon(d1);
		} catch (AlreadyExistsException e1) {
			System.out.println(e1.getMessage());
		}

		System.out.println("******************update coupon*************************");
		try {
			Coupon coupon = companiesFacade.getCompanyCoupons().get(0);
			coupon.setPrice(600);
			companiesFacade.updateCoupon(coupon);
			PrintMethod.print(companiesFacade.getCompanyCoupons());
		} catch ( OperationNotAllowedException e1) {
			System.out.println(e1.getMessage());
		}

		System.out.println("******************update coupon not working*************************");
		try {
			Coupon coupon = companiesFacade.getCompanyCoupons().get(0);
			coupon.setId(4);
			companiesFacade.updateCoupon(coupon);
		} catch (OperationNotAllowedException e) {

			System.out.println(e.getMessage());
		}

		System.out.println("******************delete coupon*************************");
		companiesFacade.deleteCoupon(6);

		System.out.println("******************get all coupons*************************");
		PrintMethod.print(companiesFacade.getCompanyCoupons());
		
		System.out.println("******************get all coupons by catagory*************************");
		PrintMethod.print(companiesFacade.getCompanyCoupons(Category.SHOOES));
		
		System.out.println("******************get all coupons by max price*************************");
		PrintMethod.print(companiesFacade.getCompanyCoupons(700));
		
		System.out.println("******************get company details*************************");
		PrintMethod.print(companiesFacade.getCompanyDetalis());
		}
	}

