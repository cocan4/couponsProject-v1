package com.couponGeneral.testclr;

import com.couponGeneral.facade.AdminFacade;
import com.couponGeneral.facade.CompaniesFacade;
import com.couponGeneral.facade.CustomerFacade;
import com.couponGeneral.login.ClientType;
import com.couponGeneral.login.LoginManager;
import com.couponGeneral.utils.ArtUtils;

public class LoginManagerTesting {

	public static void main(String[] args) {
		
		TestManager.testManager();
		
		System.out.println(ArtUtils.LOGIN_MANAGER_ART);
		
		AdminFacade a1 = (AdminFacade) LoginManager.login("admin@admin.com", "admin", ClientType.Administrator);
		CompaniesFacade c1 =(CompaniesFacade) LoginManager.login("nike@gmail.com", "12233" ,ClientType.Company);
		CustomerFacade cs1=(CustomerFacade) LoginManager.login("ido@gmail.com", "12334", ClientType.Customer);
		
	
		
		
	}

}
