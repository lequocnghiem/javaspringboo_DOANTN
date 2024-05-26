package com.api.thuctaptotnghiepbackend.Repository.Product;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.api.thuctaptotnghiepbackend.Entity.Productstore;

public interface ProductstoreRepository extends MongoRepository<Productstore, String> {
       Page<Productstore> findAll(Pageable pageable);
}

