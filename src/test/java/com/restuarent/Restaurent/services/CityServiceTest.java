package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.City;
import com.restuarent.Restaurent.models.State;
import com.restuarent.Restaurent.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.restuarent.Restaurent.services.CountryServiceTest.generateMultiPolygon;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    public static final String CITY_CODE = "city_code";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_ID = "city_id";
    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getRepository() {

        CityRepository result = SUT.getRepository();
        assertNotNull(result);

    }

    @Test
    void create() {
        when(repository.save(any(City.class))).thenReturn(city_entity());

        City result = SUT.create(city_entity());
        ArgumentCaptor<City> ac = ArgumentCaptor.forClass(City.class);

        verify(repository, times(1)).save(ac.capture());
        assertEquals(ac.getValue().getId(), result.getId());

    }



    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(city_list());

        List<City> result = SUT.getAll();

        verify(repository, times(1)).findAll();
        assertEquals(4, result.size());

    }



    @Test
    void getById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(city_entity()));
        Optional<City> result = SUT.getById(CITY_ID);

        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).findById(ac.capture());
        assertEquals(ac.getValue(), result.get().getId());

    }

    @Test
    void deleteById() {

        doNothing().when(repository).deleteById(anyString());

        String result = SUT.deleteById(CITY_ID);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).deleteById(ac.capture());
        assertTrue(result.contains(ac.getValue()));


    }

    public static City city_entity() {
        City s_entity = new City();

        s_entity.setCode(CITY_CODE);
        s_entity.setName(CITY_NAME);
        s_entity.setId(CITY_ID);
        GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());
        s_entity.setGeometry(geometry);
        return s_entity;
    }

    public static List<City> city_list() {
        List<City> c_list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            City s_entity = new City();

            s_entity.setCode(CITY_CODE);
            s_entity.setName(CITY_NAME);
            s_entity.setId(CITY_ID);
            GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());
            s_entity.setGeometry(geometry);

            c_list.add(s_entity);

        }

        return c_list;
    }
}