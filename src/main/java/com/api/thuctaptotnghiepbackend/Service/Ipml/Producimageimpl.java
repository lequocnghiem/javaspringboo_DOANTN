package com.api.thuctaptotnghiepbackend.Service.Ipml;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.api.thuctaptotnghiepbackend.Entity.Productimage;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductimageRepository;
import com.api.thuctaptotnghiepbackend.Service.ProducimageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Producimageimpl implements ProducimageService {
    
 private ProductimageRepository productimageRepository;

@Override
public List<Productimage> createProductImages(List<Productimage> productImages) {
    return productimageRepository.saveAll(productImages);
}




    @Override
    public Productimage getProductByIdimage(Long productId) {
        Optional<Productimage> optionalProduct = productimageRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public Page<Productimage> getAllProductsimage(Pageable pageable) {
        return productimageRepository.findAll(pageable);
    }

    @Override
    public List<Productimage> getProductImagesByProductId(Long productId) {
        return productimageRepository.findByProductId(productId);
    }



    @Override
    public Productimage updateProductimage(Productimage productimage) {
        // Kiểm tra tồn tại sản phẩm trước khi cập nhật
        Optional<Productimage> existingProduct = productimageRepository.findById(productimage.getId());
        if (existingProduct.isPresent()) {
            // Cập nhật thông tin sản phẩm
            Productimage updatedProduct = existingProduct.get();
            updatedProduct.setImageData(productimage.getImageData());
             updatedProduct.setIsPrimary(productimage.getIsPrimary());
         

            return productimageRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            return null;
        }
    }
   

    @Override
    public void deleteProductimage(Long productId) {
        productimageRepository.deleteById(productId);
    }





}
