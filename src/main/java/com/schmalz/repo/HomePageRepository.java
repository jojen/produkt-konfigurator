package com.schmalz.repo;


import com.schmalz.model.HomePage;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HomePageRepository extends MongoRepository<HomePage, String> {
}
