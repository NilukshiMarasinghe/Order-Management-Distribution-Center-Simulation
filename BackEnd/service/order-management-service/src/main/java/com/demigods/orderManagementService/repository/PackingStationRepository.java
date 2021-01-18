package com.demigods.orderManagementService.repository;
import com.demigods.orderManagementService.model.PackingStation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PackingStationRepository extends MongoRepository<PackingStation, String> {
    Optional<PackingStation> findById(long id);
    List<PackingStation> findByIsAvailable(boolean isAvailable);
}

