package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Productoption;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductoptionSevice;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class Productoptionimpl implements ProductoptionSevice {
    

private ProductoptionRepository productoptionRepository;

    @Override
    public Productoption createOption(Productoption productoption) {
        return productoptionRepository.save(productoption);
    }

    @Override
    public Productoption getProductById(Long productId) {
        Optional<Productoption> optionalProduct = productoptionRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public Page<Productoption> getAllProducts(Pageable pageable) {
        return productoptionRepository.findAll(pageable);
    }

    @Override
    public Productoption updateProduct(Productoption productoption) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        Optional<Productoption> existingProduct = productoptionRepository.findById(productoption.getId());
        if (existingProduct.isPresent()) {
            // Cập nhật thông tin sản phẩm
            Productoption updatedProduct = existingProduct.get();
            updatedProduct.setName(productoption.getName());
            updatedProduct.getProduct().setId(productoption.getProduct().getId());
            updatedProduct.setUpdatedAt(productoption.getUpdatedAt());
          

            return productoptionRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            return null;
        }
    }
   

    @Override
    public void deleteProduct(Long productId) {
        productoptionRepository.deleteById(productId);
    }

}
