package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.converters.CountryToGeoJsonConverter;
import com.restuarent.Restaurent.exceptions.CountryNotFoundException;
import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryToGeoJsonConverter converters;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Country> countries = countryService.getAll();

        return ResponseEntity.ok(
                countries.stream().map(item -> converters.convert(item))
        );
    }

    @PostMapping("")
    public ResponseEntity<?> createCountry(@Valid @RequestBody Country country) {
        Country result = countryService.create(country);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountry(@PathVariable String id) {
        Country country = countryService.getById(id).orElseThrow(() -> new CountryNotFoundException(id));
        return ResponseEntity.ok(converters.convert(country));
    }
}
