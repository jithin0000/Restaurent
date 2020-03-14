package com.restuarent.Restaurent.repository;

import com.restuarent.Restaurent.models.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository extends MongoRepository<Country, String> {
}
