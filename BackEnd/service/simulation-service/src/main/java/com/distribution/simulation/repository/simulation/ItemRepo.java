package com.distribution.simulation.repository.simulation;


import com.distribution.simulation.entity.simulation.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    Item findByProductId(String prodId);

}
