package com.api.thuctaptotnghiepbackend.Repository.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.thuctaptotnghiepbackend.Entity.Productimage;

public interface ProductimageRepository extends MongoRepository<Productimage,String>  {
    Page<Productimage> findAll(Pageable pageable);
}
