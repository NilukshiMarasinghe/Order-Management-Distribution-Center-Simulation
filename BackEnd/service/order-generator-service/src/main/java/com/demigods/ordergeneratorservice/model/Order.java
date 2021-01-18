package com.demigods.ordergeneratorservice.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.demigods.ordergeneratorservice.dto.OrderStatus;
import com.demigods.ordergeneratorservice.util.OrderGeneratorUtils;

public class Order {

	@Id
	private String id;
	private List<OrderItem> items;
	private OrderStatus status;
	private Date createdTime;
	private Date completedTime;
	private long timeTaken;
	private double totalWeight;

	public Order() {}
	
	public Order(List<OrderItem> items) {
		super();
		this.items = items;
		this.status = OrderStatus.NEW;
		this.createdTime = new Date();
		this.calculateTotalWeight();
	}

	public String getId() {
		return id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
		this.calculateTimeTaken();
	}
	
	private void calculateTimeTaken() {
		if(this.completedTime != null && this.createdTime != null)
			this.timeTaken = (this.completedTime.getTime() - this.createdTime.getTime()) / 1000;
	}
	
	public long getTimeTaken() {
		return this.timeTaken;
	}
	
	public void calculateTotalWeight() {
		this.totalWeight = OrderGeneratorUtils.getTotalWeight(this.items);
	}
	
	public double getTotalWeight() {
		return this.totalWeight;
	}
}
