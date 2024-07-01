package com.api.thuctaptotnghiepbackend.Service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductInventory;

public interface ProductInventoryManagementService {

    ProductInventory addProductInventory(Long productId, Long colorId, Long sizeId, int quantity);

    ProductInventory updateProductInventory(ProductInventory productInventory);
    void deleteProductInventory(Long inventoryId);
    
    ProductInventory getProductById(Long productId);

    List<ProductInventory> getProductInventoryByProductId(Long productId);

     Page<ProductInventory> getAllProductInventory(Pageable pageable);
}

