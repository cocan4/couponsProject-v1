package com.couponGeneral;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.couponGeneral.beans.Coupon;
import com.couponGeneral.dao.CouponsDAO;
import com.couponGeneral.dbdao.CouponsDBDAO;

public class Dailytask implements  Runnable {
	
	private CouponsDAO couponsDAO = null;
	
	private boolean quit = false;
	
	public Dailytask() {
		this.couponsDAO=new CouponsDBDAO();
	}

	public void run() {
		
		while(quit=false) {
			List<Coupon>coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					couponsDAO.deleteCouponCustomerVsCoupon(coupon.getId());
					couponsDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(1000*60*60*24);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
	}

	public void stop() {
		quit=true;
	}
}
