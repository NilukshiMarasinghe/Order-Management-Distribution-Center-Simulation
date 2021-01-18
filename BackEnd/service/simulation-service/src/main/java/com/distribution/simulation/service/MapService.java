package com.distribution.simulation.service;

import com.distribution.simulation.entity.simulation.PackingStation;

import java.util.List;

public interface MapService {

    List<PackingStation> getPackingStations();

    PackingStation getPackingStationById(Long id);
}
