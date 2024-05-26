package com.api.thuctaptotnghiepbackend.Service.Ipml;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Productsale;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductsaleRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductsaleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductsaleServiceImpl implements ProductsaleService {

    private final ProductsaleRepository productsaleRepository;

    @Override
    public Productsale createProductsale(Productsale productsale) {
        return productsaleRepository.save(productsale);
    }

    @Override
    public Productsale getProductsaleById(String productsaleId) {
        return productsaleRepository.findById(productsaleId).orElse(null);
    }

    @Override
    public Page<Productsale> getAllProductsales(Pageable pageable) {
        return productsaleRepository.findAll(pageable);
    }

    @Override
    public Productsale updateProductsale(Productsale productsale) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        return productsaleRepository.findById(productsale.getId()).map(existingProduct -> {
            // Cập nhật thông tin sản phẩm
            existingProduct.setSalePrice(productsale.getSalePrice());
            existingProduct.setQuantitySold(productsale.getQuantitySold());
            existingProduct.setDateStart(productsale.getDateStart());
            existingProduct.setDateEnd(productsale.getDateEnd());
            existingProduct.setUpdatedAt(productsale.getUpdatedAt());
            return productsaleRepository.save(existingProduct);
        }).orElse(null);
    }

    @Override
    public void deleteProductsale(String productsaleId) {
        productsaleRepository.deleteById(productsaleId);
    }
}

