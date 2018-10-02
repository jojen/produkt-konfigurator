package com.schmalz.repo;

import com.schmalz.model.ProductOption;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductOptionRepository extends MongoRepository<ProductOption, String> {

}
