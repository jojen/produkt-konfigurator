package com.schmalz.repo;


import com.schmalz.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
