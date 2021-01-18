package com.demigods.ordergeneratorservice.actor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.demigods.ordergeneratorservice.dto.RegistrationDTO;
import com.demigods.ordergeneratorservice.model.Order;
import com.demigods.ordergeneratorservice.service.RegisterService;
import com.demigods.ordergeneratorservice.util.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.AbstractActor;


@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderNotifier extends AbstractActor {
	
	Logger logger = LoggerFactory.getLogger(OrderNotifier.class);
	
	@Autowired
	private RegisterService registerService;
	
	RestClient restClient = new RestClient();
	
	ObjectMapper mapper = new ObjectMapper();
	
	public static class NotifyObservers {
		private Order order;

		public NotifyObservers(Order order) {
			this.order = order;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}
		
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
                .match(NotifyObservers.class, this::notify)
                .build();
	}
	
	private void notify(NotifyObservers notifyObs) {
		logger.info("Recieved NotifyObservers message.");
		List<RegistrationDTO> registrations = registerService.getRegistrations();
		
		String json;
		try {
			json = mapper.writeValueAsString(notifyObs.getOrder());
		} catch (JsonProcessingException e) {
			json = "";
		}
        for (RegistrationDTO registration : registrations) {
        	logger.info("Notifying "+registration.getName() + " at "+registration.getUri());
            try {
				restClient.post(registration.getUri(), RestClient.JSON_TYPE, json);
			} catch (Exception e) {
				logger.error("Error occurred when notifying "+registration.getName()+ " error : "+e.getMessage());
				continue;
			}
        }
	}

}
