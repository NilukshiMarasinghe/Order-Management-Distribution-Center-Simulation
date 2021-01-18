package com.distribution.worker.repository.worker;


import com.distribution.worker.entity.worker.TaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskRecord, Long>, QuerydslPredicateExecutor<TaskRecord> {
    List<TaskRecord> findByWorkerId(Long id);
}
