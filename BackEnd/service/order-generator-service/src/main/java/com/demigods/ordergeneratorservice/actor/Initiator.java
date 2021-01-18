package com.demigods.ordergeneratorservice.actor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.demigods.ordergeneratorservice.dto.ProductResponseDTO;
import com.demigods.ordergeneratorservice.dto.RegistrationDTO;
import com.demigods.ordergeneratorservice.model.Product;
import com.demigods.ordergeneratorservice.repository.ProductRepository;
import com.demigods.ordergeneratorservice.util.RestClient;
import com.demigods.ordergeneratorservice.util.ServerProps;
import com.demigods.ordergeneratorservice.util.WebResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.AbstractActor;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Initiator extends AbstractActor {
	
	Logger logger = LoggerFactory.getLogger(Initiator.class);
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	Environment environment;
	
	@Autowired
	private ServerProps serverProps;

	ObjectMapper mapper = new ObjectMapper();
	
	List<Product> products = new ArrayList<Product>();
	
	public static class GetProducts {}
	
	public static class SubscribeToClockService {}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
                .match(GetProducts.class, this::getProducts)
                .match(SubscribeToClockService.class, this::subscribeToClockService)
                .build();
	}
	
	private void getProducts(GetProducts command) {
		logger.info("Fetching products from simulator service");
		RestClient restClient = new RestClient();
		ProductResponseDTO prodResponse;
		try {
			WebResponse response = restClient.get(environment.getProperty("simulator.service.list_product_route"));
			prodResponse = mapper.readValue(response.getPayload(), new TypeReference<ProductResponseDTO>() {
			});
			prodRepository.deleteAll();
			prodRepository.saveAll(prodResponse.getContent());
			getSender().tell(new OrderGenerator.GenerateOrder(null), getSelf());
		} catch (Exception e) {
			logger.error("Error occured when fetching product list.");
			logger.error("Error: "+e.getMessage());
		}
	}
	
	private void subscribeToClockService(SubscribeToClockService command) {
		logger.info("Subscribing to clock service step counter.");
		RestClient restClient = new RestClient();
		String stepRoute = String.format("http://%s:%s/order/step", serverProps.getRemote_hostAddress(), serverProps.getPort());
		try {
			String json = mapper.writeValueAsString(new RegistrationDTO(stepRoute, "Order Generator Service"));
			restClient.post(environment.getProperty("clock.service.register"),
					RestClient.JSON_TYPE, json);

		} catch (Exception e) {
			logger.error("Error occured when subscribing to clock service.");
			logger.error("Error: "+e.getMessage());
		}
	}

}
