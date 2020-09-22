package com.couponGeneral.beans;

public class CompanyVsCoupons {

	private int companyID;
	private int couponID;
	public CompanyVsCoupons() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompanyVsCoupons(int companyID, int couponID) {
		super();
		this.companyID = companyID;
		this.couponID = couponID;
	}
	
	public CompanyVsCoupons(int couponID) {
		super();
		this.couponID = couponID;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	@Override
	public String toString() {
		return "CompanyVsCoupons [companyID=" + companyID + ", couponID=" + couponID + "]";
	}
	
	
	
}
