package com.couponGeneral.testclr.dbdaotesting;

import com.couponGeneral.dbdao.CompaniesDBDAO;
import com.couponGeneral.beans.Company;
import com.couponGeneral.db.DataBaseManager;
import com.couponGeneral.utils.ArtUtils;
import com.couponGeneral.utils.PrintMethod;

public class CompanyTesting {

	public static void main(String[] args) {
		DataBaseManager.createDropTables();

		System.out.println(ArtUtils.COMPANY_DBDAO_ART);

		System.out.println("***********ADD_COMPANY*************");
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		Company c1 = new Company("Jvc", "jvc@gmail.com", "112233");
		Company c2 = new Company("Coca-cola", "coke@gmail.com", "132233");
		Company c3 = new Company("Nikey", "nike@gmail.com", "12233");
		Company c4 = new Company("Samsung", "samsung@gmail.com", "12233");
		companiesDBDAO.addCompany(c1);
		companiesDBDAO.addCompany(c2);
		companiesDBDAO.addCompany(c3);
		companiesDBDAO.addCompany(c4);
		PrintMethod.print(companiesDBDAO.getAllCompanies());

		System.out.println("***********UPDATE_COMPANY*************");
		c1.setEmail("adada");
		c1.setId(1);
		companiesDBDAO.updateCompany(c1);
		PrintMethod.print(companiesDBDAO.getOneCompany(c1.getId()));

		System.out.println("***********GET_ONE_COMPANY*************");
		c3.getId();
		PrintMethod.print(companiesDBDAO.getOneCompany(3));

		System.out.println("***********DELETE_COMPANY*************");
		companiesDBDAO.deleteCompany(c2.getId());
		PrintMethod.print(companiesDBDAO.getAllCompanies());

	}

}
