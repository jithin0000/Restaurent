package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.Restaurant;
import com.restuarent.Restaurent.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Optional;

import static com.restuarent.Restaurent.services.RestaurantServiceTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantControllerTest {

    public static final String API_RESTAURANT = "/api/restaurant";
    @Autowired
    private TestRestTemplate template;

    @MockBean
    private RestaurantService service;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {

        when(service.getAll()).thenReturn(restaurant_list());

        ResponseEntity<String> response = template.getForEntity(API_RESTAURANT, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).getAll();
    }

    @Test
    void createRestaurant() {

        when(service.create(any(Restaurant.class))).thenReturn(valid_restuarant());

        ResponseEntity<String> response = template.postForEntity(API_RESTAURANT, valid_restuarant(), String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ArgumentCaptor<Restaurant> ac = ArgumentCaptor.forClass(Restaurant.class);
        verify(service, times(1)).create(ac.capture());

        assertTrue(response.getBody().contains(ac.getValue().getId()));

    }

    @Test
    void createRestaurantInvalidBody(){

        Restaurant invalid_body = new Restaurant();
        ResponseEntity<String> response = template.postForEntity(API_RESTAURANT, invalid_body, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(service, times(0)).create(invalid_body);

    }


    @Test
    void getRestaurant() {

        when(service.getById(anyString())).thenReturn(Optional.of(valid_restuarant()));

        ResponseEntity<String> response = template.getForEntity(API_RESTAURANT + "/" + RESTAURANT_ID, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);

        verify(service, times(1)).getById(ac.capture());
        assertTrue(response.getBody().contains(ac.getValue()));

    }

    @Test
    void getRestaurantInvalidException_404() {


        ResponseEntity<String> response = template.getForEntity(API_RESTAURANT + "/" + RESTAURANT_ID, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains(RESTAURANT_ID));

    }




    @Test
    void deleteRestaurant() {

        when(service.deleteById(anyString())).thenReturn("deleted item with id " + RESTAURANT_ID);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(API_RESTAURANT + "/delete/" + RESTAURANT_ID, HttpMethod.DELETE, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}