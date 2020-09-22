package com.couponGeneral.dao;

import java.util.List;

import com.couponGeneral.beans.Coupon;
import com.couponGeneral.beans.CouponVsCustomer;

public interface CouponsDAO {

	void addCoupon(Coupon coupon);
	void updateCoupon(Coupon coupon);
	void deleteCoupon(int couponID);
	List<Coupon>getAllCoupons();
	Coupon getOneCoupon(int couponID);
	void addCouponPurchase(int customerID,int couponID);
	void deleteCouponPurchase(int costumerID,int couponID);
	void deleteCouponCustomerVsCoupon(int couponID);
	List<CouponVsCustomer>getCustomerVsCoupons(int customerID);
	List<Coupon>getCompanyCoupons(int companyID);
	
	
}
