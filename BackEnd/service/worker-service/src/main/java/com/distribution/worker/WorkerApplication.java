package com.distribution.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.distribution"},exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class WorkerApplication {

    public static void main(String[] args) {

        System.setProperty("spring.config.name","bootstrap");
        SpringApplication.run(WorkerApplication.class, args);
    }

}
