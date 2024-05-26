package com.api.thuctaptotnghiepbackend.Repository.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Productoption;
@Repository
public interface ProductoptionRepository extends JpaRepository<Productoption,Long> {
    Page<Productoption> findAll(Pageable pageable);
    List<Productoption> findByproductId(Long productId);
    List<Productoption> findByIdIn(List<Long> optionIds);
}
