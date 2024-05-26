package com.api.thuctaptotnghiepbackend.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long productId);

    Page<Product> getAllProducts(Pageable pageable);

    Product updateProduct(Product product);

    void deleteProduct(Long productId);
    // public void saveImage(MultipartFile file) throws IOException;
    List<Product> searchProducts(String searchKeyword);
    List<Product> findProductsByPriceRange(double minPrice, double maxPrice) ;
    List<Product> findProductsByPriceAndValue(Double minPrice, Double maxPrice, List<String> values);
    Map<String, List<Map<String, String>>> getProductOptionsAndValues(Long productId);

    void addColorsToProduct(Long productId, List<Long> colorIds,List<Long> sizeId );
    void updateColorsOfProduct(Long productId, List<Long> newColorIds,List<Long> newSizeIds);
}
