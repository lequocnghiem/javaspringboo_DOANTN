package com.api.thuctaptotnghiepbackend.Repository.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;
@Repository
public interface ProductoptionvalueRepository extends JpaRepository<Productoptionvalue,Long> {
    Page<Productoptionvalue> findAll(Pageable pageable);
    
  List<Productoptionvalue> findByproductId(Long productId);
  List<Productoptionvalue> findByvalueIn(List<String> values);

  List<Productoptionvalue> findByProductoption_IdAndProduct_Id(Long optionId, Long productId);
  
}