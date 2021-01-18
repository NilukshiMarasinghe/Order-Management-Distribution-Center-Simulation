package com.demigods.ordergeneratorservice.actor;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demigods.ordergeneratorservice.akkaintegration.SpringProps;
import com.demigods.ordergeneratorservice.dto.StepDTO;
import com.demigods.ordergeneratorservice.model.Order;
import com.demigods.ordergeneratorservice.model.Product;
import com.demigods.ordergeneratorservice.repository.OrderRepository;
import com.demigods.ordergeneratorservice.repository.ProductRepository;
import com.demigods.ordergeneratorservice.util.OrderGeneratorUtils;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderGenerator extends AbstractActor {
	
	Logger logger = LoggerFactory.getLogger(OrderGenerator.class);

	@Autowired
	private ActorSystem system;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository prodRepository;
	
	ActorSelection initiator;

	public static class GenerateOrder {
		private StepDTO step;

		public GenerateOrder(StepDTO step) {
			this.step = step;
		}

		public StepDTO getStep() {
			return this.step;
		}

	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(GenerateOrder.class, this::generateOrder).build();
	}

	private void generateOrder(GenerateOrder genOrder) {
		Random rand = new Random();
		
		if (rand.nextInt(100) < 20) {
			
			logger.info("Fetching product list from database.");
			List<Product> products = prodRepository.findAll();
			
			if (!products.isEmpty()) {
				logger.info("Starting order generation.");

				Order order = orderRepo
						.save(new Order(OrderGeneratorUtils.generate(products)));
				
				logger.info("Orders saved to db. Asking OrderNotifier to NotifyObservers");
				ActorRef orderNotifierActor = system.actorOf(SpringProps.create(system, OrderNotifier.class));
				orderNotifierActor.tell(new OrderNotifier.NotifyObservers(order), getContext().getSelf());
			} else {
				logger.info("Product list is empty. Asking initiator to fetch again.");
				if(initiator == null) initiator = system.actorSelection("/user/initiator");
				initiator.tell(new Initiator.GetProducts(), getSelf());
			}
		}
		
	}

}
