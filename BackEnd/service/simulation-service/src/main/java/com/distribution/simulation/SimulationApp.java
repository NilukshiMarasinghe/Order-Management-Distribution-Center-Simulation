package com.distribution.simulation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"com.distribution"},exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class SimulationApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("spring.config.name","bootstrap");
		SpringApplication.run(SimulationApp.class, args);
	}
}
