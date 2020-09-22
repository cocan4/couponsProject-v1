package com.couponGeneral.beans;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String passWord;
	private List<Coupon> coupons= new ArrayList<>();

	
	

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String firstName, String lastName, String email, String passWord) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passWord = passWord;
	
	}
	

	public Customer(int id, String firstName, String lastName, String email, String passWord) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passWord = passWord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return passWord;
	}

	public void setPassWord(String password) {
		this.passWord = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}
	

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", passWord=" + passWord + ", coupons=" + coupons + "]";
	}

	
	
}
