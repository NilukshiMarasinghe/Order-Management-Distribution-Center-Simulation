package com.distribution.worker.service.impl;


import com.distribution.worker.entity.worker.Worker;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.repository.worker.WorkerRepo;
import com.distribution.worker.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepo workerRepo;

    @Override
    public List<Worker> getAllWorkers() {
        return this.workerRepo.findAll();
    }

    @Override
    public List<Worker> getWorkersByStatus(boolean isAvailable) {
        return this.workerRepo.findByIsAvailable(isAvailable);
    }

    @Override
    public Worker getByName(String name) {

        Worker worker = null;
        try {
            worker = this.workerRepo.findByName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("worker", "getByName");
        }

        if (worker == null)
            throw new ComplexValidationException("worker", "workerNotExists");

        return worker;
    }
}
