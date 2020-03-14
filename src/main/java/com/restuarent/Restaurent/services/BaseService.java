package com.restuarent.Restaurent.services;

import com.restuarent.Restaurent.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T extends MongoRepository<U, String>, U> {

    @Autowired
    private T repository;

    public BaseService(T repository) {
        this.repository = repository;
    }


    public T getRepository() {
        return repository;
    }

    public U create(U entity) {
        return repository.save(entity);
    }

    public List<U> getAll() {
        return repository.findAll();
    }

    public Optional<U> getById(String id) {
        return repository.findById(id);
    }

    public String deleteById(String id) {
        repository.deleteById(id);
        return "item deleted with this id "+id;
    }
}
