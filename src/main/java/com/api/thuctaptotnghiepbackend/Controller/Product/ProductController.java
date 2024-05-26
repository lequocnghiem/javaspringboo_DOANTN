package com.api.thuctaptotnghiepbackend.Controller.Product;




import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Entity.Productoption;
import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductRepository;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionRepository;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionvalueRepository;
import com.api.thuctaptotnghiepbackend.Request.ProductRequest;
import com.api.thuctaptotnghiepbackend.Service.ProductService;


@RestController
@AllArgsConstructor
@RequestMapping("api/product")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductController {

    private ProductService productService;
private ProductoptionvalueRepository productoptionvalueRepository;
private ProductoptionRepository productoptionRepository;
private ProductRepository productRepository ;
    // Create Product REST API
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest requestDto) {
        Product product = requestDto.getProduct();
        List<Long> colors = requestDto.getColorIds();
        List<Long> sizes = requestDto.getSizeIds();
      
        // Tạo hoặc cập nhật sản phẩm
        Product createdProduct = productService.createProduct(product);
        
        // Liên kết sản phẩm với color và size
        productService.addColorsToProduct(createdProduct.getId(), colors,sizes);
       
        
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
       
        
    }

   
    // Get Product by id REST API
    // http://localhost:8080/api/Products/1
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long ProductId) {
        Product Product = productService.getProductById(ProductId);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }




    
    

    // Get All Products REST API
    // http://localhost:8080/api/Products

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProducts(pageable);

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    // Update Product REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Products/1
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long ProductId,
            @RequestBody ProductRequest requestDto) {
                Product productToUpdate = requestDto.getProduct();
                productToUpdate.setId(ProductId);
                Product updatedProduct = productService.updateProduct(productToUpdate);
                
                // Cập nhật màu sắc và kích thước của sản phẩm
                List<Long> colorIds = requestDto.getColorIds();
                List<Long> sizeIds = requestDto.getSizeIds();
                productService.updateColorsOfProduct(ProductId, colorIds, sizeIds);
                
                return ResponseEntity.ok().body(updatedProduct);
    }

    // Delete Product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long ProductId) {
        productService.deleteProduct(ProductId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }



    @GetMapping("/search/{searchKeyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String searchKeyword) {
        try {
            List<Product> searchResults = productService.searchProducts(searchKeyword);
            return new ResponseEntity<>(searchResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

@GetMapping("/{productId}/options-and-values")
public List<Object> getProductOptionsAndValues(@PathVariable Long productId) {
    // Lấy danh sách Productoptionvalue dựa trên productId
    List<Productoptionvalue> productOptionValues = productoptionvalueRepository.findByproductId(productId);

    // Lấy danh sách Productoption có id trong Productoptionvalue
    List<Productoption> productOptions = productoptionRepository.findByIdIn(
            productOptionValues.stream()
                    .filter(value -> value.getProductoption().getId() != null)
                    .map(productOptionValue -> productOptionValue.getProductoption().getId())
                    .collect(Collectors.toList())
    );

    // Lọc danh sách Productoptionvalue để chỉ giữ lại những phần tử có optionId trùng với id trong Productoption
    List<Productoptionvalue> filteredProductOptionValues = productOptionValues.stream()
            .filter(value -> value.getProductoption().getId() != null && productOptions.stream()
                    .anyMatch(option -> option.getId().equals(value.getProductoption().getId())))
            .collect(Collectors.toList());

    // Tạo JSON response trực tiếp từ danh sách đã lọc
    List<Object> result = new ArrayList<>();
    for (Productoption productOption : productOptions) {
        Map<String, Object> optionMap = new HashMap<>();
        optionMap.put("productOptionId", productOption.getId());
        optionMap.put("productOptionName", productOption.getName());
        // optionMap.put("productOptionCreatedAt", productOption.getCreatedAt().toString());
        // optionMap.put("productOptionUpdatedAt", productOption.getUpdatedAt().toString());

        // Lọc Productoptionvalue tương ứng với optionId của Productoption
        List<Map<String, Object>> values = filteredProductOptionValues.stream()
                .filter(value -> value.getProductoption().getId().equals(productOption.getId()))
                .map(value -> {
                    Map<String, Object> valueMap = new HashMap<>();
                    valueMap.put("productOptionValueId", value.getId());
                    valueMap.put("productOptionValue", value.getValue());
                    valueMap.put("Option Id", value.getProductoption().getId());
                    valueMap.put("productId", value.getProduct().getId());
                    return valueMap;
                })
                .collect(Collectors.toList());

        optionMap.put("productOptionValues", values);
        result.add(optionMap);
    }

    return result;
}



@GetMapping("/search")
public ResponseEntity<List<Product>> findProductsByPriceAndValue(
    @RequestParam(required = false)  Double minPrice,
    @RequestParam(required = false)  Double maxPrice,
    @RequestParam(required = false)  List<String >values) {

        // Trường hợp có giá từ khoảng này đến khoảng khác và giá trị mặc định là null
        List<Product> products = productService.findProductsByPriceAndValue(minPrice, maxPrice, values);
        return ResponseEntity.ok(products);
   
     
    
}
@GetMapping("/{productId}/options")
public ResponseEntity<Map<String, List<Map<String, String>>>> getProductOptionsAndValuess(@PathVariable Long productId) {
    Map<String, List<Map<String, String>>> result = productService.getProductOptionsAndValues(productId);
    return ResponseEntity.ok(result);
}



}
