package com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.ProductColor;

@Repository
public interface ProductcolorRepository extends JpaRepository<ProductColor, Long> {
}
