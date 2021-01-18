package com.demigods.orderManagementService;

import javax.annotation.PostConstruct;

import com.demigods.orderManagementService.Util.ServerProps;
import com.demigods.orderManagementService.akka.actor.Initiator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demigods.orderManagementService.akka.actor.OrderProcessor;
import com.demigods.orderManagementService.akka.integration.SpringProps;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@SpringBootApplication
public class OrderManagementServiceApplication {

	Logger logger = LoggerFactory.getLogger(OrderManagementServiceApplication.class);

	@Autowired
	private ActorSystem system;

	@Autowired
	private ServerProps serverProps;

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementServiceApplication.class, args);
	}

	@PostConstruct
	public void initialize() {

		serverProps.setServerProps();

		logger.info("Creating Order Processor actor");
		system.actorOf(SpringProps.create(system, OrderProcessor.class), "OrderProcessorActor");

		logger.info("Creating Initiator actor");
		ActorRef initiatorRef = system.actorOf(SpringProps.create(system, Initiator.class), "initiator");

		initiatorRef.tell(new Initiator.SubscribeToOrderGeneratorService(), ActorRef.noSender());
	}

}
