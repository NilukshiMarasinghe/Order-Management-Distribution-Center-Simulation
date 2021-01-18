package com.demigods.orderManagementService.repository;

import com.demigods.orderManagementService.model.Order;
import com.demigods.orderManagementService.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    // Get all orders where the status is not equal to the provided status ; Sort them in ascending order by created time
    List<Order> findByStatusNotOrderByCreatedTimeAsc(OrderStatus status);
    List<Order> findByStatus(OrderStatus status);
}
