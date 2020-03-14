package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.models.Restaurant;
import com.restuarent.Restaurent.repository.CountryRepository;
import com.restuarent.Restaurent.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService extends BaseService<RestaurantRepository, Restaurant> {
    public RestaurantService(RestaurantRepository repository) {
        super(repository);
    }




}
