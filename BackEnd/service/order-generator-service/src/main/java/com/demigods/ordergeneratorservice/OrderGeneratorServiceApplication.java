package com.demigods.ordergeneratorservice;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demigods.ordergeneratorservice.actor.Initiator;
import com.demigods.ordergeneratorservice.actor.OrderGenerator;
import com.demigods.ordergeneratorservice.akkaintegration.SpringProps;
import com.demigods.ordergeneratorservice.util.ServerProps;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@SpringBootApplication
public class OrderGeneratorServiceApplication {
	
	Logger logger = LoggerFactory.getLogger(OrderGeneratorServiceApplication.class);
	
	@Autowired
	private ServerProps serverProps;
	
	@Autowired
	private ActorSystem system;

	public static void main(String[] args) {
		SpringApplication.run(OrderGeneratorServiceApplication.class, args);
	}

	@PostConstruct
	void init() {
		serverProps.setServerProps();
		
		logger.info("Creating order generator actor.");
		system.actorOf(SpringProps.create(system, OrderGenerator.class), "order-generator");
		
		logger.info("Creating initiator actor.");
		ActorRef initiatorRef = system.actorOf(SpringProps.create(system, Initiator.class), "initiator");
		
		logger.info("Sending GetProducts message to initiator.");
		initiatorRef.tell(new Initiator.GetProducts(), ActorRef.noSender());
		
		logger.info("Sending SubscribeToClockService message to initiator.");
		initiatorRef.tell(new Initiator.SubscribeToClockService(), ActorRef.noSender());
	}
}
