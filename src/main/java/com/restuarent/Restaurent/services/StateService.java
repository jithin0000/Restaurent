package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.models.State;
import com.restuarent.Restaurent.repository.CountryRepository;
import com.restuarent.Restaurent.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService extends BaseService<StateRepository, State> {
    public StateService(StateRepository repository) {
        super(repository);
    }



}
