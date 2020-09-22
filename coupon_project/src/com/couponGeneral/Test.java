package com.couponGeneral;

import com.couponGeneral.exeptions.NotExistsExeption;
import com.couponGeneral.testclr.testgeneral.TestAllFunc;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, NotExistsExeption {
		Class.forName("com.mysql.cj.jdbc.Driver");
		TestAllFunc.testAll();
		
	
	
	
	
	}

}
