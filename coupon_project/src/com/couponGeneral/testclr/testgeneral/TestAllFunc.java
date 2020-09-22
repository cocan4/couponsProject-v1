package com.couponGeneral.testclr.testgeneral;

import com.couponGeneral.Dailytask;
import com.couponGeneral.db.ConnectionPool;
import com.couponGeneral.exeptions.NotExistsExeption;
import com.couponGeneral.testclr.LoginManagerTesting;
import com.couponGeneral.testclr.TestManager;
import com.couponGeneral.testclr.dbdaotesting.CompanyTesting;
import com.couponGeneral.testclr.dbdaotesting.CouponTesting;
import com.couponGeneral.testclr.dbdaotesting.CustomerTesting;
import com.couponGeneral.testclr.facadetesting.AdminFacadeClr;
import com.couponGeneral.testclr.facadetesting.CompanyFacadeClr;
import com.couponGeneral.testclr.facadetesting.CustomerFacadeClr;

public class TestAllFunc  {

	public static void testAll() throws NotExistsExeption {
		
		Thread t1 = new Thread(new Dailytask());
		TestManager.testManager();
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		LoginManagerTesting.main(null);
		CouponTesting.main(null);
		CustomerTesting.main(null);
		CompanyTesting.main(null);
		CompanyFacadeClr.main(null);
		CustomerFacadeClr.main(null);
		AdminFacadeClr.main(null);
		
		t1.stop();
		try {
			ConnectionPool.getInstance().closeAllConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
