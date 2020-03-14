package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.exceptions.CountryNotFoundException;
import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createCountry(@Valid @RequestBody Country country) {
        Country result = countryService.create(country);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountry(@PathVariable String id) {
        Country department = countryService.getById(id).orElseThrow(() -> new CountryNotFoundException(id));
        return ResponseEntity.ok(department);
    }
}
