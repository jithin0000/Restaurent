package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryServiceTest {

    public static final String COUNTRY_ID = "country_id";
    public static final String FIRST_ID = COUNTRY_ID + 0;
    public static final String COUNTRY_CODE = "country_code";
    public static final String COUNTRY_NAME = "country_name";
    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CountryService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createNewCountrySuccess(){
        Country entity = validCountry();

        when(repository.save(entity)).thenReturn(entity);
        Country result = SUT.create(entity);
        ArgumentCaptor<Country> ac = ArgumentCaptor.forClass(Country.class);
        verify(repository, times(1)).save(ac.capture());
        assertEquals(COUNTRY_ID, result.getId());

    }

    @Test
    void getAllCountrySuccess(){
        when(repository.findAll()).thenReturn(c_list());
        List<Country> result = SUT.getAll();
        verify(repository, times(1)).findAll();
        assertEquals(4, result.size());

    }


    @Test
    void getById(){
        when(repository.findById(FIRST_ID)).thenReturn(Optional.of(c_list().get(0)));

        Optional<Country> result = SUT.getById(FIRST_ID);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).findById(ac.capture());
        assertEquals(FIRST_ID, ac.getValue());
        assertEquals(FIRST_ID, result.get().getId());

    }

    @Test
    void deleteBYId(){
        doNothing().when(repository).deleteById(FIRST_ID);

        String result = SUT.deleteById(FIRST_ID);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).deleteById(ac.capture());
        assertEquals(FIRST_ID, ac.getValue());
        assertTrue(result.contains(FIRST_ID));

    }



    public static List<Country> c_list() {
        List<Country> clist = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Country entity = new Country();
            entity.setId(COUNTRY_ID+i);
            entity.setCode(COUNTRY_CODE+i);
            entity.setName(COUNTRY_NAME+i);
            GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());
            entity.setGeometry(geometry);
            clist.add(entity);
        }

        return clist;

    }


    public static Country validCountry() {


        Country entity = new Country();
        entity.setId(COUNTRY_ID);
        entity.setCode(COUNTRY_CODE);
        entity.setName(COUNTRY_NAME);
        GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());

        entity.setGeometry(geometry);
        return entity;
    }

    public static List<GeoJsonPolygon> generateMultiPolygon() {
         List<GeoJsonPolygon> polygons =new  ArrayList();


        for (int j =0; j <=4 ; j++) {

            List<Point> points = new ArrayList<Point>();
            for (int i =0; i <=4 ; i++) {
                points.add(new Point(2.0 * i, 4.0 * i));
            }
            GeoJsonPolygon item = new GeoJsonPolygon(points);

            polygons.add(item);
        }

        return polygons;
    }


}