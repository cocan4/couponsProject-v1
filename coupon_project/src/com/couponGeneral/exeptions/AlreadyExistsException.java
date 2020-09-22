package com.couponGeneral.exeptions;

public class AlreadyExistsException extends Exception {

	public AlreadyExistsException(String message) {
		super(message+" already exists");
	}

	
}
