package com.restuarent.Restaurent.converters;

import com.restuarent.Restaurent.dto.CGeometry;
import com.restuarent.Restaurent.dto.CustomFeatures;
import com.restuarent.Restaurent.dto.GeoJsonDto;
import com.restuarent.Restaurent.models.Country;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryToGeoJsonConverter implements Converter<Country, GeoJsonDto> {
    @Override
    public GeoJsonDto convert(Country country) {
        GeoJsonDto dto = new GeoJsonDto();


        dto.setType("FeatureCollection");
        HashMap<String, String> properties = new HashMap<>();
        properties.put("name", country.getName());
        properties.put("code", country.getCode());
        dto.setProperties(properties);


        CustomFeatures feature = new CustomFeatures();
        feature.setProperties(properties);
        feature.setType("Feature");


        CGeometry geometry = new CGeometry();

        geometry.setType("MultiPolygon");
        geometry.setCoordinates(
                country.getGeometry().getCoordinates()
                        .stream().map(poly -> poly.getCoordinates().stream().map(
                        lines -> lines.getCoordinates().stream().map(
                                point -> Arrays.asList(point.getX(), point.getY())
                        )
                )).collect(Collectors.toList())
        );

        feature.setGeometry(geometry);
        List<CustomFeatures> features = new ArrayList<>();
        features.add(feature);
        dto.setFeatures(features);


        return dto;
    }


}
