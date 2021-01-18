package com.demigods.orderManagementService.controller;
import com.demigods.orderManagementService.dto.request.task.TaskUpdateRequest;
import com.demigods.orderManagementService.dto.response.task.TaskUpdateResponse;
import com.demigods.orderManagementService.model.Order;
import com.demigods.orderManagementService.model.Task;
import com.demigods.orderManagementService.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("task")
@Validated
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<Task>> getTasksByWorker(@PathVariable("workerId") int id) {
        return new ResponseEntity<List<Task>>(taskService.getAllTasksByWorker(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<TaskUpdateResponse> updateTask(@Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return new ResponseEntity<TaskUpdateResponse>(taskService.updateTaskStatus(taskUpdateRequest), HttpStatus.OK);
    }
}