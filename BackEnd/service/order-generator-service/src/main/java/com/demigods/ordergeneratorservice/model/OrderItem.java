package com.demigods.ordergeneratorservice.model;

import org.springframework.data.annotation.Id;

public class OrderItem {
	
	@Id
	private long id;
	private Product product;
	private int qty;
	
	public OrderItem(Product product, int qty) {
		super();
		this.id = System.nanoTime();
		this.product = product;
		this.qty = qty;
	}
	
	public long getId() {
		return id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

}
