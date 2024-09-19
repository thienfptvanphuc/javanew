package com.mytech.shopmamangement.controllers;

import java.io.IOException;
import java.util.List;

import com.mytech.shopmamangement.dao.CountryDao;
import com.mytech.shopmamangement.dao.CustomerDao;
import com.mytech.shopmamangement.helpers.ServletHelper;
import com.mytech.shopmamangement.models.Country;
import com.mytech.shopmamangement.models.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customers/*")
//customers/delete
//customers/update
public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = -1538449672123531213L;

	private CustomerDao customerDao = new CustomerDao();
	private CountryDao countryDao = new CountryDao();

	private void showListCustomers(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Customer> listCustomers = customerDao.getCustomers();
		req.setAttribute("listCustomers", listCustomers);
		ServletHelper.forward(req, resp, "customers");
	}

	private void searchCustomersByName(HttpServletRequest req, HttpServletResponse resp, String searchText) throws ServletException, IOException {
		List<Customer> listCustomers = customerDao.getCustomerByName(searchText);
		req.setAttribute("listCustomers", listCustomers);
		ServletHelper.forward(req, resp, "customers");
	}
	
	private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strID = req.getParameter("id");
		int id = Integer.parseInt(strID);
		Customer customer = customerDao.getCustomerById(id);
		List<Country> listCountries = countryDao.getCountries();
		req.setAttribute("customer", customer);
		req.setAttribute("listCountries", listCountries);
		ServletHelper.forward(req, resp, "add_customer");
	}
	
	private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("customer", null);
		List<Country> listCountries = countryDao.getCountries();
		req.setAttribute("listCountries", listCountries);
		ServletHelper.forward(req, resp, "add_customer");
	}
	
	private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strID = req.getParameter("id");
		int id = Integer.parseInt(strID);
		Customer customer = customerDao.getCustomerById(id);
		if (customer != null) {
			customerDao.deleteCustomer(customer);
			ServletHelper.redirect(req, resp, getServletContext().getContextPath() + "/customers");
		} else {
			ServletHelper.forward(req, resp, "not_found");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();
		action = action != null ? action : "/";
		System.out.println("Action: " + action);
		
		String strId = req.getParameter("id");
		int id = strId != null ? Integer.parseInt(strId) : 0;
		String name= req.getParameter("name");
		String email = req.getParameter("email");
		String countryStr = req.getParameter("country");
		
		System.out.println("--------country--------: " + countryStr);
		int countryId = Integer.parseInt(countryStr);
		
		Country country = countryDao.getCountryById(countryId);
		
		Customer customer = new Customer(id, name, email, country.getName());
		customer.setCountry(country);
		
		switch (action) {
		case "/update":
			customerDao.updateCustomer(customer);
			break;
		case "/insert":
			customerDao.insertCustomer(customer);
			break;
		default:
			throw new IllegalArgumentException("Post method Unexpected value: " + action);
		}
		
		ServletHelper.redirect(req, resp, getServletContext().getContextPath() + "/customers");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();
		action = action != null ? action : "/";
		System.out.println("Action: " + action);
		
		switch (action) {
		case "/":
		case "/list":
			showListCustomers(req, resp);
			break;
		case "/search":
			String searchText = req.getParameter("searchText");
			System.out.println("Search Text: " + searchText);
			searchCustomersByName(req, resp, searchText);
			break;
		case "/new":
			addCustomer(req, resp);
			break;
		case "/edit":
			editCustomer(req, resp);
			break;
		case "/delete":
			deleteCustomer(req, resp);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

}
