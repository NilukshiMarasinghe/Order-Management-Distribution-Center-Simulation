package com.distribution.simulation.service.impl;

import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.entity.simulation.Worker;
import com.distribution.simulation.repository.simulation.WorkerRepo;
import com.distribution.simulation.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Worker view(Long id) {
        Optional<Worker> exworker = this.workerRepo.findById(id);
        if (!exworker.isPresent()) {
            throw new ComplexValidationException("worker", "view.workerNotExist");
        }
        return exworker.get();
    }

    @Override
    public Worker updateWorker(Worker worker) {
        try {
            return this.workerRepo.save(worker);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("worker", "updateWorker");
        }
    }
}
