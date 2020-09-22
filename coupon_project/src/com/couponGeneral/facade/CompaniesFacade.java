package com.couponGeneral.facade;

import java.util.ArrayList;
import java.util.List;

import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Company;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.dbdao.CouponsDBDAO;
import com.couponGeneral.dbdao.CustomerDBDAO;
import com.couponGeneral.exeptions.AlreadyExistsException;
import com.couponGeneral.exeptions.OperationNotAllowedException;

public class CompaniesFacade extends ClientFacade {

	private int companyID;
	public CompaniesFacade() {
		super();
	}

	public CompaniesFacade(int companyID) {
		super();
		this.couponsDAO = new CouponsDBDAO();
		this.customerDAO = new CustomerDBDAO();
	}

	public int getCompanyID() {
		return companyID;
	}
	
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	@Override
	public boolean login(String email, String password) {
		List<Company> companies = companiesDAO.getAllCompanies();
		for (Company company : companies) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				this.companyID = company.getId();
				System.out.println("login succsesfull!!");
				return true;
			}
		}
		System.out.println("login failed - wrong input detalis");
		return false;
	}

	public void addCoupon(Coupon coupon) throws AlreadyExistsException {
		List<Coupon> companyCoupons = couponsDAO.getCompanyCoupons(companyID);
		for (Coupon coupon2 : companyCoupons) {
			if (coupon2.getTitle().equals(coupon.getTitle())) {
				throw new AlreadyExistsException("coupon title");
			}
		}
		couponsDAO.addCoupon(coupon);
		System.out.println("coupon added successfully!!");
		// coupon id must be the same as the company
	}

	public void updateCoupon(Coupon coupon) throws OperationNotAllowedException {
		List<Coupon> companyCoupons = couponsDAO.getCompanyCoupons(companyID);
		for (Coupon coup : companyCoupons) {
			if (coup.getId() == (coupon.getId()) && coup.getCompanyId() == coupon.getCompanyId()) {
				this.couponsDAO.getOneCoupon(coupon.getId());
				this.couponsDAO.updateCoupon(coupon);
				System.out.println("update completed!!");
				return;
			}
		}
		throw new OperationNotAllowedException("coupon id or company id should not change,");
	}

	public void deleteCoupon(int couponID) {
		Coupon c1 = couponsDAO.getOneCoupon(couponID);
		if (c1.getCompanyId() == companyID) {
			this.couponsDAO.deleteCoupon(couponID);
			this.couponsDAO.deleteCouponCustomerVsCoupon(couponID);
			System.out.println("coupon deleted successfully");
			return;
		}
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = couponsDAO.getAllCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyId() == companyID) {
				result.add(coup);
			}
		}
		return result;
	}

	public List<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCatagory().equals(category)) {
				result.add(coup);
			}
		}
		return result;
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> result = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getPrice() <= maxPrice) {
				result.add(coup);
			}
		}
		return result;
	}

	public Company getCompanyDetalis() {
		List<Coupon>companiesCoupons = getCompanyCoupons();
		Company company = companiesDAO.getOneCompany(companyID);
		company.setCoupons(companiesCoupons);
		return company;
	}

}
