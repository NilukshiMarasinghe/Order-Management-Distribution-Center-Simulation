package com.demigods.ordergeneratorservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demigods.ordergeneratorservice.dto.OrderStatus;
import com.demigods.ordergeneratorservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByStatus(OrderStatus status);
	
	public List<Order> findByStatusAndCompletedTimeBetween(OrderStatus status, Date from, Date to);
}
