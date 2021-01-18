package com.demigods.ordergeneratorservice.akkaintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
@ComponentScan
public class AppConfiguration {
	
	@Autowired
    private ApplicationContext applicationContext;
 
    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("order-gen-service");
        SpringExtension.getInstance().get(system)
          .initialize(applicationContext);
        return system;
    }

}
