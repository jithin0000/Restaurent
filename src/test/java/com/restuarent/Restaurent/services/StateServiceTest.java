package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.State;
import com.restuarent.Restaurent.repository.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.restuarent.Restaurent.services.CountryServiceTest.generateMultiPolygon;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StateServiceTest {

    public static final String STATE_CODE = "state_code";
    public static final String STATE_NAME = "state_name";
    public static final String STATE_ID = "state_id";
    @Mock
    private StateRepository repository;

    @InjectMocks
    private StateService SUT;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getRepository() {
        StateRepository response = SUT.getRepository();
        assertNotNull(response);
    }


    @Test
    void create() {

        when(repository.save(any(State.class))).thenReturn(state_entity());

        State result = SUT.create(state_entity());
        ArgumentCaptor<State> ac = ArgumentCaptor.forClass(State.class);

        verify(repository, times(1)).save(ac.capture());
        assertEquals(result.getId(), ac.getValue().getId());

    }



    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(state_list());

        List<State> result = SUT.getAll();
        assertEquals(4, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById() {

        when(repository.findById(anyString())).thenReturn(Optional.of(state_entity()));
        Optional<State> result = SUT.getById(STATE_ID);

        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).findById(ac.capture());
        assertEquals(ac.getValue(), result.get().getId());

    }

    @Test
    void deleteById() {

        doNothing().when(repository).deleteById(anyString());

        String result = SUT.deleteById(STATE_ID);

        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        verify(repository, times(1)).deleteById(ac.capture());
        assertTrue(result.contains(ac.getValue()));

    }


    public static State state_entity() {
        State s_entity = new State();

        s_entity.setCode("state_code");
        s_entity.setName("state_name");
        s_entity.setId("state_id");
        GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());
        s_entity.setGeometry(geometry);
        return s_entity;
    }

    public static List<State> state_list() {

        List<State> s_list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            State s_entity = new State();

            s_entity.setCode(STATE_CODE+i);
            s_entity.setName(STATE_NAME+i);
            s_entity.setId(STATE_ID+i);
            GeoJsonMultiPolygon geometry = new GeoJsonMultiPolygon(generateMultiPolygon());
            s_entity.setGeometry(geometry);
            s_list.add(s_entity);
        }

        return s_list;
    }
}