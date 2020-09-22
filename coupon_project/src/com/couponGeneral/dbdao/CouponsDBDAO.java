package com.couponGeneral.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.couponGeneral.beans.Category;
import com.couponGeneral.beans.Coupon;
import com.couponGeneral.beans.CouponVsCustomer;
import com.couponGeneral.dao.CouponsDAO;
import com.couponGeneral.db.ConnectionPool;

public class CouponsDBDAO implements CouponsDAO {

	private static final String QUERY_INSERT = "INSERT INTO `coupons`.`coupons` ( `company_id`, `category_id`,`title`, `description`,`start_Date`, `end_Date`,`amount`, `price`,`image`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `coupons`.`coupons` SET `title` = ?,`description` = ?,`start_date` = ?,`end_date` = ?, `amount` = ?,`price` = ?,`image` = ?   WHERE (`id` = ?); ";
	private static final String QUERY_DELETE = "DELETE FROM `coupons`.`coupons` WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM coupons.coupons; ";
	private static final String QUERY_GET_ONE = "SELECT * FROM coupons.coupons WHERE (`id` = ?);";
	private static final String ADD_COUPONS_PURCHASE = "INSERT INTO customers_vs_coupons( `customer_id`,`coupon_id`) VALUES (?,?)";
	private static final String DELETE_COUPONS_PURCHASE = "DELETE FROM coupons.customers_vs_coupons WHERE (`customer_id` = ? AND `coupon_id` = ?);";
	private static final String DELETE_COUPON_VS_CUSTOMER_BY_COUPONID = "DELETE FROM coupons.customers_vs_coupons WHERE (`coupon_id`= ?)";
	private static final String SELECT_ALL_COUPONS_VS_CUSTOMER = "SELECT* FROM coupons.customers_vs_coupons";
	private static final String GET_COUPONS_FROM_COMPANY = "SELECT * FROM `coupons`.`coupons` where company_id = ?;";
	//private static final String GET_CUSTOMER_COUPONS = "SELECT* FROM coupons.customers_vs_coupons where customer_id = ?;\";";

	@Override
	public void addCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCatagory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (Date) coupon.getStartDate());
			statement.setDate(6, (Date) coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getDescription());
			statement.setDate(3, (Date) coupon.getStartDate());
			statement.setDate(4, (Date) coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setDouble(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setInt(8, coupon.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCoupon(int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCouponCustomerVsCoupon(int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_COUPON_VS_CUSTOMER_BY_COUPONID);
			statement.setInt(1, couponID);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCouponPurchase(int costumerID, int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_COUPONS_PURCHASE);
			statement.setInt(1, costumerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Coupon coupon = new Coupon(resultset.getInt(1), resultset.getInt(2),
						Category.values()[resultset.getInt(3) - 1], resultset.getString(4), resultset.getString(5),
						resultset.getDate(6), resultset.getDate(7), resultset.getInt(8), resultset.getDouble(9),
						resultset.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		Coupon coupon = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, couponID);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				coupon = new Coupon(resultset.getInt(1), resultset.getInt(2),
						Category.values()[resultset.getInt(3) - 1], resultset.getString(4), resultset.getString(5),
						resultset.getDate(6), resultset.getDate(7), resultset.getInt(8), resultset.getDouble(9),
						resultset.getString(10));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return coupon;
	}

	@Override
	public void addCouponPurchase(int costumerID, int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(ADD_COUPONS_PURCHASE);
			statement.setInt(1, costumerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public List<CouponVsCustomer> getCustomerVsCoupons(int customerID) {
		List<CouponVsCustomer> cvc = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_COUPONS_VS_CUSTOMER);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				CouponVsCustomer c1 = new CouponVsCustomer(resultset.getInt(2), resultset.getInt(1));
				cvc.add(c1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return cvc;
	}

	@Override
	public List<Coupon> getCompanyCoupons(int companyID) {
		List<Coupon> coup = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(GET_COUPONS_FROM_COMPANY);
			statement.setInt(1, companyID);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Coupon coupon = new Coupon(resultset.getInt(1), resultset.getInt(2),
						Category.values()[resultset.getInt(3) - 1], resultset.getString(4), resultset.getString(5),
						resultset.getDate(6), resultset.getDate(7), resultset.getInt(8), resultset.getDouble(9),
						resultset.getString(10));
				coup.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

		return coup;
	}

}
