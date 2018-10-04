package com.schmalz.repo;


import com.schmalz.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Optional<Product> findByTitle(String title);
    Optional<Product> findByTitleUrlFriendly(String title);
}
