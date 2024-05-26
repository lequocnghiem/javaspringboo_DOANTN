package com.api.thuctaptotnghiepbackend.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Brand;

public interface BrandService {
    Brand createBrand(Brand brand);

    Brand getBrandById(String brandId);

     Page<Brand> getAllBrands(Pageable pageable);

    Brand updateBrand(Brand brand);

    void deleteBrand(String brandId);
}