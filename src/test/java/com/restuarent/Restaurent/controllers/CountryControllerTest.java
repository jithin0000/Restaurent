package com.restuarent.Restaurent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.restuarent.Restaurent.exceptions.CountryNotFoundException;
import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

import static com.restuarent.Restaurent.services.CountryServiceTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerTest {

    public static final String INVALID_ID = "invalid_id";
    Gson gson;

    @MockBean
    private CountryService service;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void setUp() {
        gson = new Gson();
    }

    @Test
    void getAll() {

        when(service.getAll()).thenReturn(c_list());

        ResponseEntity<String> response = template.getForEntity("/api/country", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).getAll();
    }

    @Test
    void createCountry() throws JsonProcessingException, FileNotFoundException {
        when(service.create(any(Country.class))).thenReturn(validCountry());
        String body = "{\"name\":\"anerica\",\"code\":\"ameraica\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-155.54211,19.08348],[-155.68817,18.91619],[-155.93665,19.05939],[-155.90806,19.33888],[-156.07347,19.70294],[-156.02368,19.81422],[-155.06779,71.147776]]]]}}";

        HttpHeaders headers =getHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response =
                template.exchange("/api/country", HttpMethod.POST,entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ArgumentCaptor<Country> ac = ArgumentCaptor.forClass(Country.class);
        verify(service, times(1)).create(ac.capture());
        assertTrue(response.getBody().contains(COUNTRY_ID));

    }

    @Test
    void getCountryById() throws Exception{
        when(service.getById(any(String.class))).thenReturn(Optional.of(validCountry()));

        ResponseEntity<String> response = template.getForEntity("/api/country/" +COUNTRY_ID, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(service, times(1)).getById(ac.capture());
        assertTrue(response.getBody().contains(ac.getValue()));

    }

    @Test
    void getCountryInvalidId() throws Exception{

        when(service.getById(any(String.class))).thenThrow(new CountryNotFoundException(INVALID_ID));


        ResponseEntity<String> response =
                template.getForEntity("/api/country/12", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }



    private HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}