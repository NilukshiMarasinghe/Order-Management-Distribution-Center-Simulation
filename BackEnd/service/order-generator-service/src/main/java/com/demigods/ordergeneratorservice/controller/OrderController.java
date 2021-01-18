package com.demigods.ordergeneratorservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demigods.ordergeneratorservice.dto.StepDTO;
import com.demigods.ordergeneratorservice.model.Order;
import com.demigods.ordergeneratorservice.service.OrderService;

@RestController
@RequestMapping("order") 
public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllOrders(@RequestParam(value = "status", required = false) String status) {
		return new ResponseEntity<>(orderService.getAllOrders(status), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable String id) {
		return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Order order) {		
		return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK); 
	}
	
	@PutMapping(path = "/status/{id}", consumes = "text/plain", produces = "application/json")
	public ResponseEntity<?> updateOrderStatus(@PathVariable String id, @RequestBody String status) {
		return new ResponseEntity<>(orderService.updateOrderStatus(id, status), HttpStatus.OK); 
	}
	
	@PutMapping(path = "/step", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> onStep(@RequestBody StepDTO step) {
		logger.info("New step recieved.");
		orderService.generateOrder(step);
		return new ResponseEntity<>("Step recorded.", HttpStatus.OK);
	}

}
