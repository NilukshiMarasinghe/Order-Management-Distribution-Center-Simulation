package com.demigods.orderManagementService.service;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.demigods.orderManagementService.akka.actor.Notifier;
import com.demigods.orderManagementService.akka.actor.OrderProcessor;
import com.demigods.orderManagementService.akka.integration.SpringProps;
import com.demigods.orderManagementService.dto.request.order.OrderCreateRequest;
import com.demigods.orderManagementService.dto.response.order.OrderCreateResponse;
import com.demigods.orderManagementService.exception.OrderExistsException;
import com.demigods.orderManagementService.helper.ModelMapperHelper;
import com.demigods.orderManagementService.model.Order;
import com.demigods.orderManagementService.model.OrderStatus;
import com.demigods.orderManagementService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ActorSystem system;

    ActorSelection orderGeneratorActor;

    public OrderService() {
    }

    @PostConstruct
    public void initialize() {
        orderGeneratorActor = system.actorSelection("/user/OrderProcessorActor");
    }

    public Order getOrderById(String id){
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isPresent()){
           return order.get();
        }
        return null;
    }

    public Order createOrder(Order order){
        return this.orderRepository.insert(order);
    }

    public Order updateOrder(Order order){
        ActorRef notifier = system.actorOf(SpringProps.create(system, Notifier.class));
        notifier.tell(new Notifier.OrderUpdate(order), ActorRef.noSender());
        return this.orderRepository.save(order);
    }

    public List<Order> getAllPendingOrders(){
        return this.orderRepository.findByStatusNotOrderByCreatedTimeAsc(OrderStatus.COMPLETE);
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(OrderStatus status){
        return this.orderRepository.findByStatus(status);
    }

    public OrderCreateResponse queueOrder(OrderCreateRequest orderCreateRequest){
        Order order = modelMapperHelper.mapObject(orderCreateRequest, Order.class);

        Optional<Order> orderExists = this.orderRepository.findById(order.getId());

        if(orderExists.isPresent()){
            throw new OrderExistsException("Order with ID " + order.getId() + "Already exists" );
        }

        orderGeneratorActor.tell(new OrderProcessor.NewOrder(order), ActorRef.noSender());
        return new OrderCreateResponse("Order Received");
    }

}
