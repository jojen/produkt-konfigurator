package com.schmalz.repo;

import com.schmalz.model.Image;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, ObjectId> {
}
