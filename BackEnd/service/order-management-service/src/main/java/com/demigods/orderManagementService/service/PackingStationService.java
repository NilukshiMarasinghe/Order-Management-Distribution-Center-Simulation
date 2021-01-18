package com.demigods.orderManagementService.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.demigods.orderManagementService.dto.internal.PackingStationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demigods.orderManagementService.dto.common.PackingStationResponseDto;
import com.demigods.orderManagementService.dto.internal.PackingStationResponse;
import com.demigods.orderManagementService.helper.ModelMapperHelper;
import com.demigods.orderManagementService.model.PackingStation;
import com.demigods.orderManagementService.repository.PackingStationRepository;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PackingStationService {

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Autowired
    private ActorSystem system;

    ActorSelection orderGeneratorActor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PackingStationRepository packingStationRepository;

    @Autowired
    private Environment environment;


    @PostConstruct
    public void initialize() {
        orderGeneratorActor = system.actorSelection("/user/OrderProcessorActor");
    }

    public PackingStation getPackingStationById(long id){
        Optional<PackingStation> packingStation = this.packingStationRepository.findById(id);
        if(packingStation.isPresent()){
            return packingStation.get();
        }
        return null;
    }

    public PackingStation updatePackingStation(PackingStation packingStation){
        return this.packingStationRepository.save(packingStation);
    }

    public List<PackingStation> getAvailablePackingStations(){
        List<PackingStation> allPackingStations = packingStationRepository.findAll();
        if(allPackingStations.isEmpty()){
            /*Packing stations not loaded, load packing stations*/
            this.loadPackingStations();
        }
        List<PackingStation> availablePackingStations = this.packingStationRepository.findByIsAvailable(true);

        return availablePackingStations;
    }


    public void loadPackingStations(){
    	PackingStationResponseDto response = restTemplate.getForObject(
                environment.getProperty("simulator.service.packing.stations.url"), PackingStationResponseDto.class);
    	
    	PackingStationResponse[] packingStationDtos = response.getContent();

        List<PackingStation> packingStations = this.modelMapperHelper.mapList(Arrays.asList(packingStationDtos), PackingStation.class);

        this.packingStationRepository.saveAll(packingStations);
    }

}