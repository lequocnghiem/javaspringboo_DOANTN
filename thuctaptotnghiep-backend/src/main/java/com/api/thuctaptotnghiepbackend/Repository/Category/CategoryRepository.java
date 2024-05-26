package com.api.thuctaptotnghiepbackend.Repository.Category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Category;


@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
     Page<Category> findAll(Pageable pageable);
}