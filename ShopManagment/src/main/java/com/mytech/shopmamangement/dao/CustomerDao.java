package com.mytech.shopmamangement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mytech.shopmamangement.db.DbConnector;
import com.mytech.shopmamangement.models.Country;
import com.mytech.shopmamangement.models.Customer;

public class CustomerDao {
	private static final String INSERT_CUSTOMERS_SQL = "insert into s8_customers" + "  (name, email, country, country_id) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String SELECT_CUSTOMER_BY_ID = "select id, name, email, country, country_id from s8_customers where id = ?";
	private static final String SELECT_CUSTOMER_BY_EMAIL = "select id, name, email, country from s8_customers where email = ?";
	private static final String SELECT_ALL_CUSTOMERS = "select * from s8_customers";
	private static final String SEARCH_CUSTOMERS_BY_NAME = "select * from s8_customers where name like ?";
	private static final String DELETE_CUSTOMERS_SQL = "delete from s8_customers where id = ?;";
	private static final String UPDATE_CUSTOMERS_SQL = "update s8_customers set name = ?,email = ?, country = ? where id = ?;";
	
	
	public Customer insertCustomer(Customer customer) {
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getCountryStr());
			preparedStatement.setInt(4, customer.getCountry().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Inster customer execption");
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public Customer updateCustomer(Customer customer) {
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getCountryStr());
			preparedStatement.setInt(4, customer.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Inster customer execption");
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public Customer deleteCustomer(Customer customer) {
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Inster customer execption");
			e.printStackTrace();
		}
		
		return customer;
	}
	
	
	public Customer getCustomerById(int id) {
		Customer customer = null;
		CountryDao countryDao = new CountryDao();
		
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String countryStr = resultset.getString("country");
				int countryId = resultset.getInt("country_id");
				customer = new Customer(id, name, email, countryStr);
				Country country = countryDao.getCountryById(countryId);
				customer.setCountry(country);
				System.out.println("customer info: " + customer.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public List<Customer> getCustomerByName(String name) {
		List<Customer> customers = new ArrayList();
		
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_CUSTOMERS_BY_NAME);
			preparedStatement.setString(1, "%" + name + "%");
			System.out.println("Statment: " + preparedStatement.toString());
			ResultSet resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String cname = resultset.getString("name");
				String email = resultset.getString("email");
				String country = resultset.getString("country");
				Customer customer = new Customer(id, name, email, country);
				System.out.println("customer info: " + customer.toString());
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList();
		
		try (Connection connection = DbConnector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
			ResultSet resultset = preparedStatement.executeQuery();
			
			CountryDao countryDao = new CountryDao();
			
			while (resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String countryStr = resultset.getString("country");
				int countryId = resultset.getInt("country_id");
				Customer customer = new Customer(id, name, email, countryStr);
				Country country = countryDao.getCountryById(countryId);
				customer.setCountry(country);
				
				System.out.println("customer info: " + customer.toString());
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	
}
