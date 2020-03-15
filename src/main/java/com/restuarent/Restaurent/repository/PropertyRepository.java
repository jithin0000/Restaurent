package com.restuarent.Restaurent.repository;

import com.restuarent.Restaurent.models.Property;
import com.restuarent.Restaurent.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
}
