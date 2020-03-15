package com.restuarent.Restaurent.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class Address {

    @NotNull(message = "street is required field ")
    private String street;
    
    @NotNull(message="country is required field ")
    private String country;
    @NotNull(message="state is required field ")
    private String state;
    @NotNull(message="city is required field ")
    private String city;


}
