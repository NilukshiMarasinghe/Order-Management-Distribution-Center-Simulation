package com.distribution.worker.service;


import com.distribution.worker.entity.worker.TaskRecord;

import java.util.List;

public interface TaskService {

    TaskRecord save(TaskRecord taskRecord);

    List<TaskRecord> getTasksByPickerId(Long pickerId);

    void updateTask(TaskRecord record);

}
