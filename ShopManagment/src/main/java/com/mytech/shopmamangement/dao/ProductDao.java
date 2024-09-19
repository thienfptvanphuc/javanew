package com.mytech.shopmamangement.dao;

import java.util.List;

import com.mytech.shopmamangement.db.DbConnector;
import com.mytech.shopmamangement.models.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProductDao {

	public List<Product> getProducts() {
		EntityManager entityManager = DbConnector.getEntityManager();
		
		TypedQuery<Product> typedQuery = entityManager.createQuery("SELECT p FROM Product p", Product.class);
		return typedQuery.getResultList();
	}
	
	public List<Product> searchProductsByName(String name) {
		EntityManager entityManager = DbConnector.getEntityManager();
		
		TypedQuery<Product> typedQuery = entityManager.createNamedQuery(Product.QUERY_FIND_BY_NAME, Product.class);
		typedQuery.setParameter("name", "%" + name + "%");
		return typedQuery.getResultList();
		
	}
	
	public Product getProductsByCode(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		
		TypedQuery<Product> typedQuery = entityManager.createNamedQuery(Product.QUERY_FIND_BY_CODE, Product.class);
		typedQuery.setParameter("code", code);
		return typedQuery.getSingleResult();
	}
	
	public boolean addProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		boolean rowAdded = false;
		
		try {
			var trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(product);
			rowAdded = true;
			trans.commit();
		} catch (Exception e) {
			rowAdded = false;
		}
		return rowAdded;
	}
	
	public boolean updateProduct(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();
		boolean rowUpdated = false;
		
		try {
			var trans = entityManager.getTransaction();
			trans.begin();
			Product updateProduct = entityManager.find(Product.class, product.getCode());
			if(updateProduct != null) {
				updateProduct.setName(product.getName());
				updateProduct.setPrice(product.getPrice());
				if (product.getImage() != null) {
					updateProduct.setImage(product.getImage());
				}
				rowUpdated = true;
			}
			trans.commit();
		} catch (Exception e) {
			rowUpdated = false;
			System.out.println("Can not update");
		}
		return rowUpdated;
	}
	
	public boolean deleteProduct(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		boolean rowDeleted = false;
		
		try {
			var trans = entityManager.getTransaction();
			trans.begin();
			Product deleteProduct = entityManager.find(Product.class, code);
			if(deleteProduct != null) {
				entityManager.remove(deleteProduct);
				rowDeleted = true;
			}
			trans.commit();
		} catch (Exception e) {
			rowDeleted = false;
			System.out.println("Can not update");
		}
		return rowDeleted;
	}
}
