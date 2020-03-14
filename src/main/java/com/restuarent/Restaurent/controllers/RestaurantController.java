package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.exceptions.RestaurantNotFoundException;
import com.restuarent.Restaurent.models.Restaurant;
import com.restuarent.Restaurent.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.create(restaurant), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable String id) {
        Restaurant department = restaurantService.getById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String id) {

        String message = restaurantService.deleteById(id);
        return ResponseEntity.ok(message);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @Valid @RequestBody Restaurant body) {
//
//        return ResponseEntity.ok(restaurantService.updateRestaurant(id, body));
//    }


}
