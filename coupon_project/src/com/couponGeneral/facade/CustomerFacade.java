package com.couponGeneral.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Company;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.beans.CouponVsCustomer;
import com.couponGeneral.beans.Customer;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.NotExistsExeption;
import com.couponGeneral.exeptions.OperationNotAllowedException;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	public CustomerFacade() {
		super();

	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public boolean login(String email, String password) {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				this.customerID = customer.getId();
				System.out.println("login succsesfull!!");
				return true;
			}
		}
		System.out.println("login failed - wrong input detalis");
		return false;
	}

	public void purchaseCoupon(Coupon coupon)
			throws AlreadyExistsException, OperationNotAllowedException, NotExistsExeption {
		List<CouponVsCustomer> customerCoupons = couponsDAO.getCustomerVsCoupons(customerID);
		coupon = couponsDAO.getOneCoupon(coupon.getId());
		for (CouponVsCustomer coup : customerCoupons) {
			if (coup.getCouponID() == coupon.getId()) {
				throw new AlreadyExistsException("coupon");
			}
		}
		if (coupon.getAmount() == 0) {
			throw new OperationNotAllowedException("no coupons left! ");
		}
		if (coupon.getEndDate().before(new Date())) {
			throw new OperationNotAllowedException("date expaierd! ");
		}
		couponsDAO.addCouponPurchase(this.customerID, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponsDAO.updateCoupon(coupon);
		System.out.println("Coupon Purchased Successfully");

	}

	public List<Coupon> getCustomerCoupons() {
		List<CouponVsCustomer> customerVsCoupons = couponsDAO.getCustomerVsCoupons(customerID);
		List<Coupon> result = new ArrayList<>();
		for (CouponVsCustomer cvc : customerVsCoupons) {
			if (cvc.getCustomerID() == this.customerID) {
				result.add(couponsDAO.getOneCoupon(cvc.getCouponID()));
			}
		}
		return result;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> customerCoupons = getCustomerCoupons();
		List<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getPrice() <= maxPrice) {
				coups.add(coupon);
			}
		}

		return coups;
	}

	public Customer getCustomerDetalis() {
		List<Coupon>customerCoupons = getCustomerCoupons();
		Customer customer = customerDAO.getOneCustomer(customerID);
		customer.setCoupons(customerCoupons);
		return customer;
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		List<Coupon> customerCoupons = getCustomerCoupons();
		List<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getCatagory().equals(category)) {
				coups.add(coupon);
			}
		}
		return coups;
	}

	public int getCustomerIdFromDbdao(String email, String password) {

		return customerDAO.getCustomerID(email, password);
	}
}
