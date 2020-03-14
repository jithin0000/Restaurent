package com.restuarent.Restaurent.repository;

import com.restuarent.Restaurent.models.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {
}
