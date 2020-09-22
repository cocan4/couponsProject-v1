package com.couponGeneral.login;

import com.couponGeneral.facade.AdminFacade;
import com.couponGeneral.facade.ClientFacade;
import com.couponGeneral.facade.CompaniesFacade;
import com.couponGeneral.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public static ClientFacade login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Administrator:
			AdminFacade adminFacade = new AdminFacade();
			if (adminFacade.login(email, password)) {
				return adminFacade;
			}
			break;
		case Company:
			CompaniesFacade companiesFacade = new CompaniesFacade();
			if (companiesFacade.login(email, password)) {
				return companiesFacade;
			}
			break;
		case Customer:
			CustomerFacade customerFacade = new CustomerFacade();
			if (customerFacade.login(email, password)) {
				return customerFacade;
			}
		default:
			break;
		}
		return null;
	}
}
