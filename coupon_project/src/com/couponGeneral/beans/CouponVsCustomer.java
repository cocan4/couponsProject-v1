package com.couponGeneral.beans;

public class CouponVsCustomer {

	private int couponID;
	private int customerID;
	
	
	public CouponVsCustomer(int couponID) {
		super();
		this.couponID = couponID;
	}

	public CouponVsCustomer(int couponID, int customerID) {
		this.couponID = couponID;
		this.customerID = customerID;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "CouponVsCustomer [couponID=" + couponID + ", customerID=" + customerID + "]";
	}
	
	
	
}
