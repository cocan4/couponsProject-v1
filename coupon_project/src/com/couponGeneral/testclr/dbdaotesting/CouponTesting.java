package com.couponGeneral.testclr.dbdaotesting;

import java.sql.Date;

import com.couponGeneral.dbdao.CompaniesDBDAO;
import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Company;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.beans.Customer;
import com.couponGeneral.db.DataBaseManager;
import com.couponGeneral.dbdao.CouponsDBDAO;
import com.couponGeneral.dbdao.CustomerDBDAO;
import com.couponGeneral.utils.ArtUtils;
import com.couponGeneral.utils.PrintMethod;

public class CouponTesting {

	public static void main(String[] args) {
		
		DataBaseManager.createDropTables();
		
		System.out.println(ArtUtils.COUPON_DBDAO_ART);
		
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		Company c1 = new Company("Jvc", "jvc@gmail.com", "112233");
		Company c2 = new Company("Coca-cola", "coke@gmail.com", "132233");
		Company c3 = new Company("Nikey", "nike@gmail.com", "12233");
		Company c4 = new Company("Samsung", "samsung@gmail.com", "12233");
		companiesDBDAO.addCompany(c1);
		companiesDBDAO.addCompany(c2);
		companiesDBDAO.addCompany(c3);
		companiesDBDAO.addCompany(c4);

		CustomerDBDAO customerDBDAO = new CustomerDBDAO();

		Customer a1 = new Customer("ido", "shay", "ido@gmail.com", "12334");
		Customer a2 = new Customer("moran", "shay", "morani@gmail.com", "12234");
		Customer a3 = new Customer("ziv", "shay", "zivi@gmail.com", "13234");

		customerDBDAO.addCustomer(a1);
		customerDBDAO.addCustomer(a2);
		customerDBDAO.addCustomer(a3);

		CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
		
		System.out.println("***********ADD_COUPON_DBDAO*************");
		Coupon d1 = new Coupon(1, Category.ELECTRICITY, "SOUND-SYSTEM", "bluetooth car audio",
				Date.valueOf("2020-09-01"), Date.valueOf("2020-09-30"), 10, 100, "sound");
		Coupon d2 = new Coupon(2, Category.FOOD, "DRINKS", "six-pack of coca kola bottels ", Date.valueOf("2020-09-01"),
				Date.valueOf("2020-09-30"), 10, 64, "drinks");
		Coupon d3 = new Coupon(3, Category.SHOOES, "Nike Basketball Shooes", "Air jordn 11 retro",
				Date.valueOf("2020-09-01"), Date.valueOf("2020-09-30"), 10, 550, "shoes");
		Coupon d4 = new Coupon(4, Category.ELECTRICITY, "SOUND-SYSTEM", "bluetooth car audio",
				Date.valueOf("2020-09-01"), Date.valueOf("2020-09-30"), 10, 100, "sound");

		couponsDBDAO.addCoupon(d1);
		couponsDBDAO.addCoupon(d2);
		couponsDBDAO.addCoupon(d3);
		couponsDBDAO.addCoupon(d4);
		PrintMethod.print(couponsDBDAO.getAllCoupons());

		System.out.println("***********GET_ALL_COUPON_DBDAO*************");
		PrintMethod.print(couponsDBDAO.getAllCoupons());

		System.out.println("***********UPDATE_COUPON_DBDAO*************");
		d1=couponsDBDAO.getOneCoupon(1);
		d1.setPrice(129.9);
		d2.setImage("aaabbb");
		couponsDBDAO.updateCoupon(d1);
		couponsDBDAO.updateCoupon(d2);
		couponsDBDAO.updateCoupon(d3);
		couponsDBDAO.updateCoupon(d4);
		PrintMethod.print(couponsDBDAO.getAllCoupons());

		System.out.println("***********GET_ONE_COUPON_DBDAO*************");
		PrintMethod.print(couponsDBDAO.getOneCoupon(3));

		System.out.println("***********DELETE_COUPON_DBDAO*************");
		couponsDBDAO.deleteCoupon(3);
		PrintMethod.print(couponsDBDAO.getAllCoupons());

		System.out.println("***********ADD_PURCHASE_COUPON_DBDAO*************");
		couponsDBDAO.addCouponPurchase(1, 1);
		couponsDBDAO.addCouponPurchase(1, 2);
		PrintMethod.print(couponsDBDAO.getAllCoupons());

		System.out.println("***********DELETE_PURCHASE_COUPON_DBDAO*************");
		couponsDBDAO.deleteCouponPurchase(1, 2);
		PrintMethod.print(couponsDBDAO.getAllCoupons());


	}

}
