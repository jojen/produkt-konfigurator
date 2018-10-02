package com.schmalz.repo;


import com.schmalz.model.Customer;
import com.schmalz.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerRepository extends MongoRepository<Customer, String> {  }
