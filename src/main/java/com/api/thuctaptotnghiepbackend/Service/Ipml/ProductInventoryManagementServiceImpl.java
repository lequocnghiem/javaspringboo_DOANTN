package com.api.thuctaptotnghiepbackend.Service.Ipml;



import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Entity.ProductInventory;
import com.api.thuctaptotnghiepbackend.Entity.ProductSize;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductInventoryRepository;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductRepository;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductcolorRepository;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductsizeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.thuctaptotnghiepbackend.Service.ProductInventoryManagementService;

@Service
public class ProductInventoryManagementServiceImpl implements ProductInventoryManagementService {

    private final ProductInventoryRepository productInventoryRepository;
    private final ProductRepository productRepository;
    private final ProductcolorRepository productColorRepository;
    private final ProductsizeRepository productSizeRepository;

    @Autowired
    public ProductInventoryManagementServiceImpl(ProductInventoryRepository productInventoryRepository,
                                                ProductRepository productRepository,
                                                ProductcolorRepository productColorRepository,
                                                ProductsizeRepository productSizeRepository) {
        this.productInventoryRepository = productInventoryRepository;
        this.productRepository = productRepository;
        this.productColorRepository = productColorRepository;
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    @Transactional
    public ProductInventory addProductInventory(Long productId, Long colorId, Long sizeId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        ProductColor color = productColorRepository.findById(colorId)
                .orElseThrow(() -> new RuntimeException("ProductColor not found with id: " + colorId));

        ProductSize size = productSizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("ProductSize not found with id: " + sizeId));

        ProductInventory existingInventory = productInventoryRepository.findByProductAndColorAndSize(product, color, size);
        if (existingInventory != null) {
            throw new RuntimeException("Inventory already exists for product " + productId + ", color " + colorId + ", size " + sizeId);
        }

        ProductInventory newInventory = new ProductInventory();
        newInventory.setProduct(product);
        newInventory.setColor(color);
        newInventory.setSize(size);
        newInventory.setQuantity(quantity);

        return productInventoryRepository.save(newInventory);
    }
    @Override
    @Transactional
    public ProductInventory updateProductInventory(ProductInventory productInventory) {

        System.out.println("Updating ProductInventory with id: " + productInventory.getProduct());
        Optional<ProductInventory> existingProduct = productInventoryRepository.findById(productInventory.getId());
        if (existingProduct.isPresent()) {
            ProductInventory updatedProduct = existingProduct.get();
            // Cập nhật thông tin từ productInventory vào updatedProduct
            updatedProduct.setProduct(productInventory.getProduct());
            updatedProduct.setColor(productInventory.getColor());
            updatedProduct.setSize(productInventory.getSize());
            updatedProduct.setQuantity(productInventory.getQuantity());
            
            // Lưu và trả về sản phẩm đã cập nhật
            return productInventoryRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            throw new RuntimeException("Không tìm thấy ProductInventory với id: " + productInventory.getId());
        }
    }




 





    @Override
    @Transactional
    public void deleteProductInventory(Long inventoryId) {
        productInventoryRepository.deleteById(inventoryId);
    }

    @Override
    public Page<ProductInventory> getAllProductInventory(Pageable pageable) {
        // Implement getAllProductInventory logic here
        return productInventoryRepository.findAll(pageable);
    }


    @Override
    public ProductInventory getProductById(Long productId) {
        return productInventoryRepository.findById(productId)
                .orElse(null); // Handle case where product inventory with given ID is not found
    }


    public List<ProductInventory> getProductInventoryByProductId(Long productId) {
        return productInventoryRepository.findByProductId(productId);
    }
    
}

