package com.mytech.shopmamangement.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.mytech.shopmamangement.dao.ProductDao;
import com.mytech.shopmamangement.helpers.Constants;
import com.mytech.shopmamangement.helpers.ServletHelper;
import com.mytech.shopmamangement.models.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/products/*")
@MultipartConfig(fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -4378739717485150422L;

	private ProductDao productDao;

	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return Constants.DEFAULT_FILENAME;
	}

	@Override
	public void init() throws ServletException {
		super.init();
		productDao = new ProductDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("products:-------------------");
		String action = req.getParameter("action");
		String code = req.getParameter("code");
		String search = req.getPathInfo();
		
		System.out.println("search: " + search);
		
		if (search != null) {
			String searchText = req.getParameter("searchText");
			List<Product> listProducts = productDao.searchProductsByName(searchText);
			req.setAttribute("listProducts", listProducts);
			ServletHelper.forward(req, resp, "products");
			return;
		}

		action = action != null ? action : "/";

		switch (action) {
		case "add":
			ServletHelper.forward(req, resp, "add_product");
			break;
		case "update":
			Product product = productDao.getProductsByCode(code);
			req.setAttribute("product", product);
			ServletHelper.forward(req, resp, "update_product");
			break;
		case "delete":
			productDao.deleteProduct(code);
			ServletHelper.redirect(req, resp, getServletContext().getContextPath() + "/products");
			break;
		default:
			List<Product> listProducts = productDao.getProducts();
			req.setAttribute("listProducts", listProducts);
			ServletHelper.forward(req, resp, "products");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String strPrice = req.getParameter("price");
		double price = Double.parseDouble(strPrice);

		// Upload file
		String fileName = "";
		String relativeWebPath = "";
		String uploadPath = getServletContext().getRealPath(relativeWebPath) + Constants.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		System.out.println("Upload dir: " + uploadDir);
		System.out.println("Upload path: " + uploadPath);

		try {
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (!"".equals(fileName)) {
					part.write(uploadPath + File.separator + fileName);
				}
			}
			req.setAttribute("upload_status", "File " + fileName + " has uploaded successfully!");
			req.setAttribute("upload_path", Constants.UPLOAD_DIRECTORY + File.separator + fileName);

		} catch (FileNotFoundException fne) {
			req.setAttribute("upload_status", "There was an error: " + fne.getMessage());
		} catch (IOException e) {
			req.setAttribute("upload_status", "No file to upload!");
		}
		// End upload file
		
		String image = fileName.equals("") ? null : Constants.UPLOAD_DIRECTORY + File.separator + fileName;
		Product product = new Product(code, name, price, image);

		String action = req.getParameter("action");

		if ("update".equals(action)) {
			productDao.updateProduct(product);
		} else {
			productDao.addProduct(product);
		}

//		if (code == null) {
//			
//		} else {
//			productDao.updateProduct(product);
//		}

		ServletHelper.redirect(req, resp, getServletContext().getContextPath() + "/products");

	}
}
