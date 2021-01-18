package com.demigods.ordergeneratorservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demigods.ordergeneratorservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
}
