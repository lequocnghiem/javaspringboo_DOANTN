package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductcolorRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductcolorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductcolorServiceImpl implements ProductcolorService {
    



    
    private ProductcolorRepository productColorRepository;

    @Override
    public List<ProductColor> getAll() {
        return productColorRepository.findAll();
    }

    @Override
    public Optional<ProductColor> getById(Long id) {
        return productColorRepository.findById(id);
    }

    @Override
    public ProductColor add(ProductColor productColor) {
        return productColorRepository.save(productColor);
    }

    @Override
    public ProductColor update(Long id, ProductColor updatedProductColor) {
        Optional<ProductColor> existingProductColor = productColorRepository.findById(id);
        if (existingProductColor.isPresent()) {
            updatedProductColor.setId(id);
            return productColorRepository.save(updatedProductColor);
        } else {
            throw new IllegalArgumentException("Product color with ID " + id + " does not exist.");
        }
    }

    @Override
    public void delete(Long id) {
        productColorRepository.deleteById(id);
    }
}
