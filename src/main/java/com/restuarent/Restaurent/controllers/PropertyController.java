package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.Property;
import com.restuarent.Restaurent.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createProperty(@Valid @RequestBody Property body) {
        return new ResponseEntity<>(service.create(body), HttpStatus.CREATED);
    }
}

















