package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Property;
import com.restuarent.Restaurent.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService extends BaseService<PropertyRepository, Property> {
    public PropertyService(PropertyRepository repository) {
        super(repository);
    }
}
