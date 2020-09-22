package com.couponGeneral.exeptions;

public class NotExistsExeption extends Exception {

	public NotExistsExeption(String message) {
		super("there is no "+message);
		
	}

}
