package com.demigods.orderManagementService.controller;

import com.demigods.orderManagementService.dto.request.order.OrderCreateRequest;
import com.demigods.orderManagementService.dto.response.order.OrderCreateResponse;
import com.demigods.orderManagementService.model.Order;
import com.demigods.orderManagementService.model.OrderStatus;
import com.demigods.orderManagementService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
@Validated
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getAllOrdersByStatus(@PathVariable("status") OrderStatus status) {
        return new ResponseEntity<List<Order>>(orderService.getOrdersByStatus(status), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderCreateResponse> createOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        return new ResponseEntity<OrderCreateResponse>(orderService.queueOrder(orderCreateRequest), HttpStatus.OK);
    }
}
