package com.distribution.simulation.repository.simulation;

import com.distribution.simulation.entity.simulation.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AisleRepo extends JpaRepository<Aisle,Long>, QuerydslPredicateExecutor<Aisle> {
}
