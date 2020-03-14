package com.restuarent.Restaurent.repository;

import com.restuarent.Restaurent.models.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
