package com.restuarent.Restaurent.dto;

import lombok.Data;
import org.geojson.Geometry;
import org.geojson.Point;

import java.util.HashMap;
import java.util.List;

@Data
public class CustomFeatures {

    private String type;
    private HashMap<String, String> properties;

    private CGeometry geometry;


}
