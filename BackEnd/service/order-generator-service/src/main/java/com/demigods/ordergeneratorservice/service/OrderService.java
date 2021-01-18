package com.demigods.ordergeneratorservice.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demigods.ordergeneratorservice.actor.OrderGenerator;
import com.demigods.ordergeneratorservice.dto.OrderStatus;
import com.demigods.ordergeneratorservice.dto.StepDTO;
import com.demigods.ordergeneratorservice.exception.IllegalParameterException;
import com.demigods.ordergeneratorservice.exception.RecordNotFoundException;
import com.demigods.ordergeneratorservice.model.Order;
import com.demigods.ordergeneratorservice.repository.OrderRepository;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

@Service
public class OrderService {
	
	@Autowired
    private ActorSystem system;
	
	@Autowired
	private OrderRepository orderRepo;
	
	ActorSelection orderGeneratorActor;
	
	@PostConstruct
    public void initialize(){
		orderGeneratorActor = system.actorSelection("/user/order-generator");
    }
	
	public void generateOrder(StepDTO step) {
		orderGeneratorActor.tell(new OrderGenerator.GenerateOrder(step), ActorRef.noSender());
	}
	
	public List<Order> getAllOrders(String status) {
		if(status != null) {
			try {
				return orderRepo.findByStatus(OrderStatus.valueOf(status.toUpperCase()));
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		return orderRepo.findAll();
	}
	
	public Order getOrder(String id) {
		if(!orderRepo.existsById(id)) throw new RecordNotFoundException("Order not found for given id.");
		
		return orderRepo.findById(id).orElse(null);
	}
	
	public Order updateOrder(String id, Order order) {
		if(!orderRepo.existsById(id)) throw new RecordNotFoundException("Order not found for given id.");
		
		return orderRepo.save(order);
	}
	
	public String updateOrderStatus(String id, String status) {
		Order order = orderRepo.findById(id).orElse(null);
		OrderStatus newStatus;
		if(order == null) {
			throw new RecordNotFoundException("Order not found for given id.");
		}
		try {
			newStatus = OrderStatus.valueOf(status.toUpperCase());
			if(newStatus == OrderStatus.COMPLETE) order.setCompletedTime(new Date());
			order.setStatus(newStatus);
			orderRepo.save(order);
			return "Order status updated";
		} catch (IllegalArgumentException e) {
			throw new IllegalParameterException("Invalid status.");
		}
	}

}
