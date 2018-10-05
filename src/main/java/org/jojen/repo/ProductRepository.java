package org.jojen.repo;


import org.jojen.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByTitle(String title);
    Optional<Product> findByTitleUrlFriendly(String title);
}
