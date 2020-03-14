package com.restuarent.Restaurent.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String id) {
        super("no country with this id "+id);
    }
}
