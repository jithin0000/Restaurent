package com.restuarent.Restaurent.dto;

import com.mongodb.client.model.geojson.Geometry;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Data
public class CGeometry {

    private String type;

    private List<Stream<Stream<List<Double>>>> coordinates;
}
