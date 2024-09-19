package com.mytech.shopmamangement.db;

import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class DbConnector {
	
	//JDBC
	private static String jdbcURL = "jdbc:mysql://localhost:3306/T52303?useSSL=false";
	private static String jdbcUsername = "approot";
	private static String jdbcPassword = "123456";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			System.out.println("Can not open connection to db");
		}
		
		return connection;
	}
	
	//JPA
	@PersistenceUnit
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("S8CustomerManagement");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
