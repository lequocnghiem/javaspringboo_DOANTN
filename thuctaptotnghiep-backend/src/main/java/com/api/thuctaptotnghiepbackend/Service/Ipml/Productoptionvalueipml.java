package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionvalueRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductoptionvalueService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class Productoptionvalueipml implements ProductoptionvalueService {
    
    
private ProductoptionvalueRepository productoptionvalueRepository;

    @Override
    public Productoptionvalue createOption(Productoptionvalue productoptionvalue) {
        return productoptionvalueRepository.save(productoptionvalue);
    }

    @Override
    public Productoptionvalue getProductById(String productId) {
        Optional<Productoptionvalue> optionalProduct = productoptionvalueRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public Page<Productoptionvalue> getAllProducts(Pageable pageable) {
        return productoptionvalueRepository.findAll(pageable);
    }

    @Override
    public Productoptionvalue updateProduct(Productoptionvalue productoptionvalue) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        Optional<Productoptionvalue> existingProduct = productoptionvalueRepository.findById(productoptionvalue.getId());
        if (existingProduct.isPresent()) {
            // Cập nhật thông tin sản phẩm
            Productoptionvalue updatedProduct = existingProduct.get();
            updatedProduct.setValue(productoptionvalue.getValue());
            updatedProduct.setOptionId(productoptionvalue.getOptionId());
             updatedProduct.setProductId(productoptionvalue.getProductId());
            updatedProduct.setUpdatedAt(productoptionvalue.getUpdatedAt());
          

            return productoptionvalueRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            return null;
        }
    }
   

    @Override
    public void deleteProduct(String productId) {
        productoptionvalueRepository.deleteById(productId);
    }




}
