package com.distribution.worker.service.impl;


import com.distribution.worker.entity.worker.TaskRecord;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.repository.worker.TaskRepo;
import com.distribution.worker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    private static final Logger logger = Logger.getLogger(TaskServiceImpl.class.getName());

    @Override
    public TaskRecord save(TaskRecord taskRecord) {
        try {
            TaskRecord record = this.taskRepo.save(taskRecord);
            return record;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("taskRecord", "persistIssue");
        }
    }

    @Override
    public List<TaskRecord> getTasksByPickerId(Long pickerId) {
        List<TaskRecord> records = new ArrayList<>();
        try {
            records = this.taskRepo.findByWorkerId(pickerId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("taskRecord", "searchIssue");
        }

        if (records.isEmpty())
            throw new ComplexValidationException("taskRecord", "recordsEmpty");
        return records;
    }

    @Override
    public void updateTask(TaskRecord record) {
        TaskUpdate.processingOrder(record);
    }
}
