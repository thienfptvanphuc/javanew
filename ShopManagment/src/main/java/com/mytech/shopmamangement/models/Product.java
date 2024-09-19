package com.mytech.shopmamangement.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "s8_products")
@NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name LIKE :name")
@NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 6820874703496256095L;
	
	public static String QUERY_FIND_BY_NAME = "Product.findByName";
	public static String QUERY_FIND_BY_CODE = "Product.findByCode";

	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column
	private double price;
	
	@Column(length = 1024)
	private String image;

	public Product() {}
	
	public Product(String code, String name, double price, String image) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

}
