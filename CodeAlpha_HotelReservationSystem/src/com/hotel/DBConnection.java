package com.hotel;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/hotelManagementSystem";
	private static final String user = "postgres";
	private static final String password = "root"; // change this

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
