package com.couponGeneral.exeptions;

public class OperationNotAllowedException extends Exception {

	public OperationNotAllowedException(String message) {
		super(message + " operation is not allowed!");
		
	}

	
}
