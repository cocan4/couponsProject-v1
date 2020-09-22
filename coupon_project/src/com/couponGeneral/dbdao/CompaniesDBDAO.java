package com.couponGeneral.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.couponGeneral.beans.Company;
import com.couponGeneral.dao.CompaniesDAO;
import com.couponGeneral.db.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

	private static final String QUERY_INSERT = "INSERT INTO `coupons`.`companies` ( `name`, `email`, `password`) VALUES ( ?, ?, ?); ";
	private static final String QUERY_UPDATE = "UPDATE `coupons`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?); ";
	private static final String QUERY_DELETE = "DELETE FROM `coupons`.`companies` WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM coupons.companies; ";
	private static final String QUERY_GET_ONE = "SELECT * FROM coupons.companies WHERE (`id` = ?);";
	private static final String QUERY_IS_COMPANY_EXISTS = "SELECT * FROM coupons.companies WHERE (`email` = ?) AND (`password` = ?);";

	@Override
	public boolean isCompanyExists(String email, String password) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXISTS);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return false;
	}

	@Override
	public void addCompany(Company company) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateCompany(Company company) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCompany(int CompanyID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, CompanyID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Company company = new Company(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getString(4));
				companies.add(company);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) {
		Company company = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, companyID);
			ResultSet resultset = statement.executeQuery();
			resultset.next();
			company = new Company(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
					resultset.getString(4));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return company;
	}

	@Override
	public int getCompanyID(String email, String password) {
		Connection conn = null;
		List<Company> result = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXISTS);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				Company company = new Company();
				result.add(company);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		if (result.size() == 1) {
			return result.get(0).getId();
		}
		return 0;
	}

}
