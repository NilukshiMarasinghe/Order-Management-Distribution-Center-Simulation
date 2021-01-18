package com.demigods.orderManagementService.service;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

import com.demigods.orderManagementService.dto.common.WorkerResponseDto;
import com.demigods.orderManagementService.dto.internal.WorkerResponse;
import com.demigods.orderManagementService.helper.ModelMapperHelper;
import com.demigods.orderManagementService.model.Worker;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class WorkerService {

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Autowired
    private ActorSystem system;

    ActorSelection orderGeneratorActor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    @PostConstruct
    public void initialize() {
        orderGeneratorActor = system.actorSelection("/user/OrderProcessorActor");
    }


    public List<Worker> getAvailableWorkers(){
    	WorkerResponseDto response = restTemplate.getForObject(
                environment.getProperty("worker.service.workers.url"), WorkerResponseDto.class);
    	
    	WorkerResponse[] workerDtos = response.getContent();

        List<Worker> workers = this.modelMapperHelper.mapList(Arrays.asList(workerDtos), Worker.class);

        return workers;
    }

}
