package org.jojen.repo;


import org.jojen.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerRepository extends MongoRepository<Customer, String> {  }
