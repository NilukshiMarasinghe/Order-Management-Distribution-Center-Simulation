package com.distribution.simulation.repository;

import com.distribution.simulation.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>, QuerydslPredicateExecutor<Authority> {
}
