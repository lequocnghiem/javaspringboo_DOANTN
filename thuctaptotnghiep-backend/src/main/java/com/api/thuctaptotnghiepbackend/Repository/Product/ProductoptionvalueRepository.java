package com.api.thuctaptotnghiepbackend.Repository.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;

public interface ProductoptionvalueRepository extends MongoRepository<Productoptionvalue,String> {
    Page<Productoptionvalue> findAll(Pageable pageable);
    

}