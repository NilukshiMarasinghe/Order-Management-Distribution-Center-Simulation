package com.distribution.worker.service;


import com.distribution.worker.entity.worker.Worker;

import java.util.List;

public interface WorkerService {

     List<Worker> getAllWorkers();

     List<Worker> getWorkersByStatus(boolean isAvailable);

     Worker getByName(String name);
}
