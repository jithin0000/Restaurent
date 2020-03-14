package com.restuarent.Restaurent.bootstrap;

import com.github.javafaker.Faker;
import com.restuarent.Restaurent.models.Restaurant;
import com.restuarent.Restaurent.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        generateRestaurent(faker);

    }

    private void generateRestaurent(Faker faker) {
        for (int i = 0; i < 200; i++) {
            Restaurant res = new Restaurant();
            res.setName(faker.funnyName().name());
            res.setLocation(new GeoJsonPoint(new Point(
                    Double.parseDouble(faker.address().longitude()),
                    Double.parseDouble(faker.address().latitude()))));
            restaurantService.create(res);
        }
    }
}
