package com.restuarent.Restaurent.Bootstrap;

import com.restuarent.Restaurent.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public void run(String... args) throws Exception {

//        Faker faker = new Faker();
//
//        generateRestaurent(faker);

    }
}
