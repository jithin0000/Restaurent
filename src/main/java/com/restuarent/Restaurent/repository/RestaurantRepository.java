package com.restuarent.Restaurent.repository;

import com.restuarent.Restaurent.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
