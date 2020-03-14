package com.restuarent.Restaurent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String id) {
        super("no restaurant with this id "+id);
    }
}
