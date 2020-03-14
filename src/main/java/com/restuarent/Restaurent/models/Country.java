package com.restuarent.Restaurent.models;

import com.mongodb.client.model.geojson.MultiPolygon;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Country extends BaseModel {

    @NotNull(message = "name is required field ")
    private String name;
    @NotNull(message="code is required field ")
    private String code;
    @NotNull(message = "geometry is required field")
    private GeoJsonMultiPolygon geometry;




}
