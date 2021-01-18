package com.distribution.simulation.repository.simulation;

import com.distribution.simulation.entity.simulation.PackingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingRepo  extends JpaRepository<PackingStation, Long>, QuerydslPredicateExecutor<PackingStation> {
}
