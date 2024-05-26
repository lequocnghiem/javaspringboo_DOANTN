package com.api.thuctaptotnghiepbackend.Service.Ipml;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Productstore;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductstoreRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductstoreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductstoreServiceImpl implements ProductstoreService {

    private final ProductstoreRepository productstoreRepository;

    @Override
    public Productstore createProductstore(Productstore productstore) {
        return productstoreRepository.save(productstore);
    }

    @Override
    public Productstore getProductstoreById(String productstoreId) {
        return productstoreRepository.findById(productstoreId).orElse(null);
    }

    @Override
    public Page<Productstore> getAllProductstores(Pageable pageable) {
        return productstoreRepository.findAll(pageable);
    }

    @Override
    public Productstore updateProductstore(Productstore productstore) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        return productstoreRepository.findById(productstore.getId()).map(existingProduct -> {
            // Cập nhật thông tin sản phẩm
            existingProduct.setQuantityAdded(productstore.getQuantityAdded());
            existingProduct.setEntryDate(productstore.getEntryDate());
            existingProduct.setUpdatedAt(productstore.getUpdatedAt());
            return productstoreRepository.save(existingProduct);
        }).orElse(null);
    }

    @Override
    public void deleteProductstore(String productstoreId) {
        productstoreRepository.deleteById(productstoreId);
    }
}
