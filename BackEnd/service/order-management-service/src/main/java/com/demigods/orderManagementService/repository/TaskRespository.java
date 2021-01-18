package com.demigods.orderManagementService.repository;
import com.demigods.orderManagementService.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRespository extends MongoRepository<Task, String> {
    List<Task> findByWorkerId(int workerId);
}
