package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.City;
import com.restuarent.Restaurent.services.CityService;
import com.restuarent.Restaurent.services.StateService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.restuarent.Restaurent.services.CityServiceTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {


    public static final String API_CITY = "/api/city";
    @Autowired
    private TestRestTemplate template;

    @MockBean
    private CityService service;

    @Test
    void getAllCitySuccess(){
        when(service.getAll()).thenReturn(city_list());

        ResponseEntity<String> response = template.getForEntity(API_CITY, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).getAll();
    }
    @Test
    void createCitySuccess(){
        when(service.create(any(City.class))).thenReturn(city_entity());

        String body = "{\"name\":\"anerica\",\"code\":\"ameraica\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-155.54211,19.08348],[-155.68817,18.91619],[-155.93665,19.05939],[-155.90806,19.33888],[-156.07347,19.70294],[-156.02368,19.81422],[-155.06779,71.147776]]]]}}";
        HttpHeaders headers =getHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = template.exchange(API_CITY, HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ArgumentCaptor<City> ac = ArgumentCaptor.forClass(City.class);
        verify(service, times(1)).create(ac.capture());
        assertTrue(response.getBody().contains(CITY_ID));

    }

    @Test
    void createCityInvalidRequest_400(){
        when(service.create(any(City.class))).thenReturn(invalid_city());

        ResponseEntity<String> response = template.postForEntity(API_CITY, invalid_city(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(service, times(0)).create(invalid_city());
    }

    private City invalid_city() {
        return new City();
    }

    private HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}










