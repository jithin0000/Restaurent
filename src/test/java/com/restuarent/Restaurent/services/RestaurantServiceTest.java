package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Restaurant;
import com.restuarent.Restaurent.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    public static final String RESTAURANT_ID = "RESTAURANT_ID";
    public static final String RESTAURANT_NAME = "name";
    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantService SUT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getRepository() {

        RestaurantRepository result = SUT.getRepository();
        assertNotNull(result);
    }

    @Test
    void create() {

        when(repository.save(any(Restaurant.class))).thenReturn(valid_restuarant());

        Restaurant result = SUT.create(valid_restuarant());
        ArgumentCaptor<Restaurant> ac = ArgumentCaptor.forClass(Restaurant.class);

        verify(repository, times(1)).save(ac.capture());
        assertEquals(ac.getValue().getId(), result.getId());

    }


    @Test
    void getAll() {

        when(repository.findAll()).thenReturn(restaurant_list());

        List<Restaurant> result = SUT.getAll();

        assertEquals(4, result.size());
        verify(repository, times(1)).findAll();
    }


    @Test
    void getById() {

        when(repository.findById(any(String.class))).thenReturn(Optional.of(valid_restuarant()));

        Optional<Restaurant> result = SUT.getById(RESTAURANT_ID);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).findById(ac.capture());

        assertEquals(RESTAURANT_ID, result.get().getId());
    }

    @Test
    void deleteById() {

        doNothing().when(repository).deleteById(anyString());

        String result = SUT.deleteById(RESTAURANT_ID);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).deleteById(ac.capture());

        assertEquals(RESTAURANT_ID, ac.getValue());
        assertTrue(result.contains(RESTAURANT_ID));

    }

    public static Restaurant valid_restuarant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(RESTAURANT_ID);
        restaurant.setName(RESTAURANT_NAME);
        GeoJsonPoint location = new GeoJsonPoint(
                new Point(2.0,3.0)
        );
        restaurant.setLocation(location);
        return restaurant;
    }


    public static List<Restaurant> restaurant_list() {

        List<Restaurant> r_list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(RESTAURANT_ID);
            restaurant.setName(RESTAURANT_NAME);
            GeoJsonPoint location = new GeoJsonPoint(
                    new Point(2.0,3.0)
            );
            restaurant.setLocation(location);
            r_list.add(restaurant);
        }


        return r_list;
    }

}