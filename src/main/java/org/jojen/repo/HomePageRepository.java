package org.jojen.repo;


import org.jojen.model.HomePage;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HomePageRepository extends MongoRepository<HomePage, String> {
}
