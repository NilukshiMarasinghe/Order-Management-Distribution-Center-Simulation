package com.demigods.orderManagementService.akka.actor;

import akka.actor.AbstractActor;
import com.demigods.orderManagementService.Util.ServerProps;
import com.demigods.orderManagementService.dto.internal.OrderGeneratorRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Initiator extends AbstractActor {

    Logger logger = LoggerFactory.getLogger(Initiator.class);

    @Autowired
    Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServerProps serverProps;


    public static class SubscribeToOrderGeneratorService {}

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SubscribeToOrderGeneratorService.class, this::subscribeToClockService)
                .build();
    }

    private void subscribeToClockService(SubscribeToOrderGeneratorService command) {
        try {
            String orderPostUri = String.format("http://%s:%s/order", serverProps.getRemote_hostAddress(), serverProps.getPort());
            OrderGeneratorRegisterRequest orderGeneratorRegisterRequest = new OrderGeneratorRegisterRequest("Order Management Service", orderPostUri);
            restTemplate.postForLocation(environment.getProperty("order.generator.service.subscribe.url"), orderGeneratorRegisterRequest);
            logger.info("Subscribed to order generator service");
        } catch (Exception e) {
            logger.error("Error occured when subscribing to order generator service.");
            logger.error("Error: "+e.getMessage());
        }
    }

}
