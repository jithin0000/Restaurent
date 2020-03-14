package com.restuarent.Restaurent.controllers;

import com.restuarent.Restaurent.models.State;
import com.restuarent.Restaurent.services.StateService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static com.restuarent.Restaurent.services.StateServiceTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StateControllerTest {

    public static final String API_STATE = "/api/state";
    @Autowired
    private TestRestTemplate template;

    @MockBean
    private StateService service;

    @Test
    void getAll() throws Exception {

        when(service.getAll()).thenReturn(state_list());

        ResponseEntity<String> response = template.getForEntity(API_STATE, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).getAll();

    }

    @Test
    void createState() throws Exception {

        when(service.create(any(State.class))).thenReturn(state_entity());

        String body = "{\"name\":\"anerica\",\"code\":\"ameraica\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-155.54211,19.08348],[-155.68817,18.91619],[-155.93665,19.05939],[-155.90806,19.33888],[-156.07347,19.70294],[-156.02368,19.81422],[-155.06779,71.147776]]]]}}";
        HttpHeaders headers =getHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = template.exchange(API_STATE, HttpMethod.POST,
                entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ArgumentCaptor<State> ac = ArgumentCaptor.forClass(State.class);
        verify(service, times(1)).create(ac.capture());
        assertTrue(response.getBody().contains(STATE_ID));
    }

    @Test
    void createStateInvalidRequest(){
        when(service.create(any(State.class))).thenReturn(invalid_state());

        ResponseEntity<String> response = template.postForEntity(API_STATE, invalid_state(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(service, times(0)).create(invalid_state());
    }

    private State invalid_state() {
        return new State();
    }

    private HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}