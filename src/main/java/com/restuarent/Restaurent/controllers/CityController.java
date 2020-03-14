package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.City;
import com.restuarent.Restaurent.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createCity(@Valid @RequestBody City city) {
        return new ResponseEntity<>(cityService.create(city), HttpStatus.CREATED);
    }


}
