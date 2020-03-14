package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.City;
import com.restuarent.Restaurent.models.Country;
import com.restuarent.Restaurent.repository.CityRepository;
import com.restuarent.Restaurent.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService extends BaseService<CityRepository, City> {
    public CityService(CityRepository repository) {
        super(repository);
    }


}
