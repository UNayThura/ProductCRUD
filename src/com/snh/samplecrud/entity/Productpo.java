package com.snh.samplecrud.entity;

import javax.validation.constraints.Min;


import org.hibernate.validator.constraints.NotEmpty;

public class Productpo {

	private int id;

	@NotEmpty(message =" Name may not be empty")
	private String name;
	@Min(value = 0, message = "Quantity should not be less than 0")
	private int quantity;
	@Min(value = 0, message = "Quantity should not be less than 0")
	private double price;
	@Min(value = 1 , message= "Select category name")
	private int categoryId;
	private String categoryName;



	public Productpo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Productpo(int id, String name, int quantity, double price, int categoryId, String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Productpo [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
