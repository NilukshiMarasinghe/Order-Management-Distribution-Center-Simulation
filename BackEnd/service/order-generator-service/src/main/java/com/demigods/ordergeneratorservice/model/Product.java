package com.demigods.ordergeneratorservice.model;

import org.springframework.data.annotation.Id;

public class Product {
	
	@Id
	private String id;
	private String productId;
	private String name;
	private Double weight;
	private String supplier;
	private String location;
	
	public Product() {}

	public Product(String productId, String name, Double weight, String supplier, String location) {
		super();
		this.productId = productId;
		this.name = name;
		this.weight = weight;
		this.supplier = supplier;
		this.location = location;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	};

}
