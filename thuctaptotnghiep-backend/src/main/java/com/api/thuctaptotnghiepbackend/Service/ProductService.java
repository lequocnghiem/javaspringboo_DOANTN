package com.api.thuctaptotnghiepbackend.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(String productId);

    Page<Product> getAllProducts(Pageable pageable);

    Product updateProduct(Product product);

    void deleteProduct(String productId);
    // public void saveImage(MultipartFile file) throws IOException;
}
