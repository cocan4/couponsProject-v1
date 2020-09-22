package com.couponGeneral.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.couponGeneral.beans.Category;

public class DataBaseManager {

	private static final String URL = "jdbc:mysql://localhost:3306/coupons?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=Asia/Jerusalem";
	private static final String USER = "root";
	private static final String PASS = "1234";

	private static final String QUERY_CREATE_COMPANIES = "CREATE TABLE `coupons`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));\r\n" + " ";
	private static final String QUERY_CREATE_CUSTOMERS = "CREATE TABLE `coupons`.`customers` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`)); ";
	private static final String QUERY_CREATE_CATEGORIES = "CREATE TABLE `coupons`.`categories` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));\r\n" + " ";
	private static final String QUERY_CREATE_COUPONS = "CREATE TABLE `coupons` (\r\n"
			+ "  `id` int NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` int NOT NULL,\r\n"
			+ "  `category_id` int NOT NULL,\r\n" + "  `title` varchar(45) NOT NULL,\r\n"
			+ "  `description` varchar(45) NOT NULL,\r\n" + "  `start_date` date NOT NULL,\r\n"
			+ "  `end_date` date NOT NULL,\r\n" + "  `amount` int NOT NULL,\r\n" + "  `price` double NOT NULL,\r\n"
			+ "  `image` varchar(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  KEY `company_id_idx` (`company_id`),\r\n" + "  KEY `category_id_idx` (`category_id`),\r\n"
			+ "  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),\r\n"
			+ "  CONSTRAINT `company_id` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) "
			+ "   ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION); ";
	private static final String QUERY_CREATE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupons`.`customers_vs_coupons` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ "    FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `coupons`.`customers` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ "    FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `coupons`.`coupons` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);\r\n" + " ";

	private static final String QUERY_DROP_COMPANIES = "DROP TABLE `coupons`.`companies`; ";
	private static final String QUERY_DROP_CUSTOMERS = "DROP TABLE `coupons`.`customers`; ";
	private static final String QUERY_DROP_CATEGORIES = "DROP TABLE `coupons`.`categories`; ";
	private static final String QUERY_DROP_COUPONS = "DROP TABLE `coupons`.`coupons`; ";
	private static final String QUERY_DROP_CUSTOMERS_VS_COUPONS = "DROP TABLE `coupons`.`customers_vs_coupons`; ";

	private static final String QUERY_INSERT_CATEGORY = "INSERT INTO `coupons`.`categories` (name) VALUES (?);";

	public static void insertCategory(Category category) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_CATEGORY);
			statement.setString(1, category.name());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	public static void insertQuery(String query) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	public static void createDropTables() {
		System.out.println("START");
		insertQuery(QUERY_DROP_CUSTOMERS_VS_COUPONS);
		insertQuery(QUERY_DROP_COUPONS);
		insertQuery(QUERY_DROP_COMPANIES);
		insertQuery(QUERY_DROP_CUSTOMERS);
		insertQuery(QUERY_DROP_CATEGORIES);

		insertQuery(QUERY_CREATE_CATEGORIES);
		insertQuery(QUERY_CREATE_CUSTOMERS);
		insertQuery(QUERY_CREATE_COMPANIES);
		insertQuery(QUERY_CREATE_COUPONS);
		insertQuery(QUERY_CREATE_CUSTOMERS_VS_COUPONS);

		for (Category category : Category.values()) {
			insertCategory(category);
		}
		System.out.println("END");
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}

}
