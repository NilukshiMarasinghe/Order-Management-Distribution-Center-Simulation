package com.demigods.ordergeneratorservice.dto;

public class WeightProcessedPerSecondMetric {
	
	private String orderId;
	private Double weightPerSec;
	
	public WeightProcessedPerSecondMetric(String orderId, Double weightPerSec) {
		super();
		this.orderId = orderId;
		this.weightPerSec = weightPerSec;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public Double getWeightPerSec() {
		return weightPerSec;
	}
}
