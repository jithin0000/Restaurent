package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.State;
import com.restuarent.Restaurent.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    private StateService stateService;


    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(stateService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> createState(@Valid @RequestBody State state) {
        State state1 = stateService.create(state);
        return new ResponseEntity<>(state1, HttpStatus.CREATED);
    }
}
