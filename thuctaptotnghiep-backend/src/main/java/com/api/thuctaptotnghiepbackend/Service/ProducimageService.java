package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Productimage;

public interface ProducimageService {

     List<Productimage> createProductImages(List<Productimage> productImages);
      void deleteProductimage(String productId);
     Productimage updateProductimage(Productimage productimage);
     Page<Productimage> getAllProductsimage(Pageable pageable);
     Productimage getProductByIdimage(String productId);
} 