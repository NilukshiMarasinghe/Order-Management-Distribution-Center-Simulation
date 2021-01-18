package com.distribution.simulation.service.impl;

import com.distribution.simulation.entity.simulation.PackingStation;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.repository.simulation.PackingRepo;
import com.distribution.simulation.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private PackingRepo packingRepo;

    @Override
    public List<PackingStation> getPackingStations() {
        return this.packingRepo.findAll();
    }

    @Override
    public PackingStation getPackingStationById(Long id) {
        Optional<PackingStation> exPack = this.packingRepo.findById(id);
        if (!exPack.isPresent()) {
            throw new ComplexValidationException("packStation", "view.itemNotExists");
        }
        return exPack.get();
    }
}
