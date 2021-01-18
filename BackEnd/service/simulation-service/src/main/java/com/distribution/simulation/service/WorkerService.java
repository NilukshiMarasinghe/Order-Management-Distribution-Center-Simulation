package com.distribution.simulation.service;

import com.distribution.simulation.entity.simulation.Worker;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    List<Worker> getWorkersByStatus(boolean isAvailable);

    Worker getByName(String name);


    Worker view(Long id);

    Worker updateWorker(Worker worker);
}
