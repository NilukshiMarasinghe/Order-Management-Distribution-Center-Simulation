package com.distribution.worker.repository.worker;


import com.distribution.worker.entity.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long>, QuerydslPredicateExecutor<Worker> {

    List<Worker> findByIsAvailable(boolean isAvailable);

    Worker findByName(String name);
}
