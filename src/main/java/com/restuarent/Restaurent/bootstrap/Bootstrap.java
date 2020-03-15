package com.restuarent.Restaurent.bootstrap;

import com.github.javafaker.Faker;
import com.restuarent.Restaurent.models.*;
import com.restuarent.Restaurent.services.BaseService;
import com.restuarent.Restaurent.services.PropertyService;
import com.restuarent.Restaurent.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PropertyService propertyService;

    @Override
    public void run(String... args) throws Exception {

        Faker usFaker = new Faker(new Locale("en-US"));


//        generateRestaurent(faker);
//        generateProperties(usFaker);

    }

    private void generateProperties(Faker faker) {

        List<PropType> typs = new ArrayList<>();
        typs.add(PropType.TEST1);
        typs.add(PropType.TEST2);
        typs.add(PropType.TEST3);
        List<RoomType> roomtyps = new ArrayList<>();
        roomtyps.add(RoomType.ENTIRE);
        roomtyps.add(RoomType.HOUSE);
        roomtyps.add(RoomType.HOTEL);
        roomtyps.add(RoomType.PART);

        for (int i = 0; i < 150; i++) {
            Property property = new Property();

            property.setName(faker.company().name());
            property.setDescription(faker.lorem().sentence(250));
            property.setPropertyType(typs.get(faker.number().numberBetween(0, 3)));
            property.setRoomType(roomtyps.get(faker.number().numberBetween(0, 4)));
            Address address = new Address();
            address.setCity(faker.address().city());
            address.setCountry(faker.address().country());
            address.setState(faker.address().state());
            GeoJsonPoint point = new GeoJsonPoint(new Point(
                    Double.parseDouble(faker.address().longitude()),
                    Double.parseDouble(faker.address().latitude())
            ));

            property.setLocation(point);
            property.setAddress(address);
            List<String> amenities = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                amenities.add(faker.funnyName().name());
            }

            property.setAmenities(amenities);
            property.setPrice(faker.number().numberBetween(500, 2500));
            property.setStartDate(faker.date().future(80,TimeUnit.DAYS));
            property.setEndDate(faker.date().future(180, TimeUnit.DAYS));

            propertyService.create(property);
        }

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
