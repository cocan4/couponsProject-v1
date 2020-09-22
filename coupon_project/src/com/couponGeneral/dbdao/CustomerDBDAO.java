package com.couponGeneral.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.couponGeneral.beans.Customer;
import com.couponGeneral.dao.CustomerDAO;
import com.couponGeneral.db.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {

	private static final String QUERY_INSERT = "INSERT INTO `coupons`.`customers` ( `first_name`, `last_name`, `email`, `password`) VALUES ( ?, ?, ?, ?); ";
	private static final String QUERY_UPDATE = "UPDATE `coupons`.`customers` SET `first_name` = ?,`last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?); ";
	private static final String QUERY_DELETE = "DELETE FROM `coupons`.`customers` WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM coupons.customers; ";
	private static final String QUERY_GET_ONE = "SELECT * FROM coupons.customers WHERE (`id` = ?);";
	private static final String QUERY_IS_COMPANY_EXISTS = "SELECT * FROM coupons.customers WHERE (`email` = ?) AND (`password` = ?); ";

	@Override
	public boolean isCustomerExists(String email, String password) {
		Connection conn = null;
		Customer customer = new Customer();
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXISTS);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				resultset.equals(customer);
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
	public void addCustomer(Customer customer) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public void deleteCustomer(int customerID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Customer customer = new Customer(resultset.getString(2), resultset.getString(3), resultset.getString(4),
						resultset.getString(5));
				customer.setId(resultset.getInt(1));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerID) {
		Customer result = null;
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, customerID);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {

				Customer customer = new Customer(resultset.getInt(1),resultset.getString(2), resultset.getString(3), resultset.getString(4),
						resultset.getString(5));
				result = customer;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);

		}

		return result;
	}

	@Override
	public int getCustomerID(String email, String password) {
		Connection conn = null;
		List<Customer> result = new ArrayList<>();
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXISTS);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				Customer customer = new Customer();
				result.add(customer);
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