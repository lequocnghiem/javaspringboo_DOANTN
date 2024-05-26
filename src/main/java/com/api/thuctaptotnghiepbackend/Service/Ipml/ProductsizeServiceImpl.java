package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.ProductSize;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductsizeRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductsizeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsizeServiceImpl implements ProductsizeService {
    


    
    private ProductsizeRepository productSizeRepository;

    @Override
    public List<ProductSize> getAll() {
        return productSizeRepository.findAll();
    }

    @Override
    public Optional<ProductSize> getById(Long id) {
        return productSizeRepository.findById(id);
    }

    @Override
    public ProductSize add(ProductSize productSize) {
        return productSizeRepository.save(productSize);
    }

    @Override
    public ProductSize update(Long id, ProductSize updatedProductSize) {
        Optional<ProductSize> existingProductSize = productSizeRepository.findById(id);
        if (existingProductSize.isPresent()) {
            updatedProductSize.setId(id);
            return productSizeRepository.save(updatedProductSize);
        } else {
            throw new IllegalArgumentException("Product size with ID " + id + " does not exist.");
        }
    }

    @Override
    public void delete(Long id) {
        productSizeRepository.deleteById(id);
    }



}
