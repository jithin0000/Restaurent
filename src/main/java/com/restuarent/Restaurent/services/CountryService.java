package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends BaseService<CountryRepository, Country> {
    public CountryService(CountryRepository repository) {
        super(repository);
    }


}
