package com.api.thuctaptotnghiepbackend.Service.Ipml;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductService;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(Product product) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            // Cập nhật thông tin sản phẩm
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setSlug(product.getSlug());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQty(product.getQty());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setCategoryId(product.getCategoryId());
            updatedProduct.setBrandId(product.getBrandId());
            updatedProduct.setMetakey(product.getMetakey());
            updatedProduct.setMetadesc(product.getMetadesc());
            updatedProduct.setCreatedAt(product.getCreatedAt());
            updatedProduct.setUpdatedAt(product.getUpdatedAt());
           
            updatedProduct.setStatus(product.getStatus());

            return productRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            return null;
        }
    }
   

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}

