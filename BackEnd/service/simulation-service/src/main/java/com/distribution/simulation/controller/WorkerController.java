package com.distribution.simulation.controller;


import com.distribution.simulation.dto.response.simulation.WorkerOut;
import com.distribution.simulation.dto.response.wrapper.ListResponseWrapper;
import com.distribution.simulation.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.simulation.entity.simulation.Worker;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.modelmapper.ModelMapper;
import com.distribution.simulation.service.WorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("${app.endpoint.getAllWorkers}")
    public ResponseEntity<ListResponseWrapper<WorkerOut>> itemMinSearch() {

        List<Worker> workerList = this.workerService.getAllWorkers();
        List<WorkerOut> out = this.modelMapper.map(workerList, WorkerOut.class);

        return new ResponseEntity<ListResponseWrapper<WorkerOut>>(
                new ListResponseWrapper<WorkerOut>(out), HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.getAllByStatus}")
    public ResponseEntity<ListResponseWrapper<WorkerOut>> getByAvailability(@PathVariable String isAvailable) {

        boolean available;
        if (isAvailable.equals("0")) {
            available = false;

        } else if (isAvailable.equals("1")) {
            available = true;
        } else {
            throw new ComplexValidationException("worker", "invalidValue");
        }
        List<Worker> workerList = this.workerService.getWorkersByStatus(available);
        List<WorkerOut> out = this.modelMapper.map(workerList, WorkerOut.class);

        return new ResponseEntity<ListResponseWrapper<WorkerOut>>(
                new ListResponseWrapper<WorkerOut>(out), HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.getByName}")
    public ResponseEntity<SimpleResponseWrapper<WorkerOut>> getByName(@PathVariable String name) {


        if (name.equals(null)) {
            throw new ComplexValidationException("worker", "nameIsNull");
        }

        Worker worker = this.workerService.getByName(name);
        WorkerOut out = this.modelMapper.map(worker, WorkerOut.class);

        return new ResponseEntity<SimpleResponseWrapper<WorkerOut>>(
                new SimpleResponseWrapper<WorkerOut>(out), HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.changeWorkerStatus}")
    public ResponseEntity<SimpleResponseWrapper<WorkerOut>> changeWorkerStatus(@PathVariable String id, @PathVariable boolean status) {

        if (id == null) {
            throw new ComplexValidationException("worker", "idIsNull");
        }
        Worker worker = this.workerService.view(new Long(id));
        worker.setAvailable(status);
        Worker updated = this.workerService.updateWorker(worker);
        WorkerOut out = this.modelMapper.map(worker, WorkerOut.class);

        return new ResponseEntity<SimpleResponseWrapper<WorkerOut>>(
                new SimpleResponseWrapper<WorkerOut>(out), HttpStatus.CREATED);
    }
}
