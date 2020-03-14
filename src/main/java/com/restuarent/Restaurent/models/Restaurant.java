package com.restuarent.Restaurent.models;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Restaurant extends BaseModel {

    @NotNull(message = "name is required field ")
    private String name;

    @NotNull(message="location is required field ")
    private GeoJsonPoint location;



}
