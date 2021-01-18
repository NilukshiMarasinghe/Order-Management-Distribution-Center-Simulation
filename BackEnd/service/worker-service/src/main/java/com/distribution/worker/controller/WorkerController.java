package com.distribution.worker.controller;


import com.distribution.worker.dto.response.worker.WorkerOut;
import com.distribution.worker.dto.response.wrapper.ListResponseWrapper;
import com.distribution.worker.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.worker.entity.worker.Worker;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.modelmapper.ModelMapper;
import com.distribution.worker.service.WorkerService;
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
}
