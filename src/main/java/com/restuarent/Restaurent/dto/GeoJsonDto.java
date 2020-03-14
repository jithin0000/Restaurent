package com.restuarent.Restaurent.dto;

import lombok.Data;
import org.geojson.Feature;
import org.geojson.FeatureCollection;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Data
public class GeoJsonDto implements Serializable {

    private String type;
    private HashMap<String, String> properties;
    private List<CustomFeatures> features;



}
