package com.api.thuctaptotnghiepbackend.Controller.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductInventory;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductInventoryRepository;
import com.api.thuctaptotnghiepbackend.Request.ProductInventoryRequest;
import com.api.thuctaptotnghiepbackend.Service.ProductInventoryManagementService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/productinventory")

@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class ProductInventoryController {

    private final ProductInventoryManagementService productInventoryService;
 private final ProductInventoryRepository productInventoryRepository;
    @Autowired
    public ProductInventoryController(ProductInventoryManagementService productInventoryService,ProductInventoryRepository productInventoryRepository) {
        this.productInventoryService = productInventoryService;
        this.productInventoryRepository = productInventoryRepository;
    }
 
    // Endpoint to add ProductInventory
  @PostMapping
    public ResponseEntity<ProductInventory> addProductInventory(
            @RequestBody ProductInventoryRequest request) {
        ProductInventory productInventory = productInventoryService.addProductInventory(
                request.getProductId(),
                request.getColorId(),
                request.getSizeId(),
                request.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(productInventory);
    }

    // Endpoint to update ProductInventory
    @PutMapping("{id}")
    public ResponseEntity<ProductInventory> updateProductInventory(
            @PathVariable("id") Long id,
            @RequestBody ProductInventory request) {

        // Thiết lập id cho request (nếu không phải truyền lên từ client)
        request.setId(id);
        System.out.println("Updating ProductInventory with id: " + request.getProduct());
        // Gọi service để cập nhật thông tin ProductInventory
        ProductInventory updatedInventory = productInventoryService.updateProductInventory(request);

        // Trả về ResponseEntity với dữ liệu cập nhật và mã HTTP 200 OK
        return ResponseEntity.ok(updatedInventory);
    }





    @PutMapping("payment/{productId}")
public ResponseEntity<?> updateProductInventorySize(
        @PathVariable("productId") Long productId,
        @RequestBody ProductInventory request) {

    // Thiết lập productId cho request (nếu không phải truyền lên từ client)
    request.getProduct().setId(productId);
    System.out.println("Updating ProductInventory with product id: " + productId);

    // Kiểm tra xem color và size có tồn tại trong ProductInventory không
    Optional<ProductInventory> productInventoryOpt = productInventoryRepository.findByProductIdAndColorIdAndSizeId(
            productId, request.getColor().getId(), request.getSize().getId());
    
    if (!productInventoryOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductInventory not found for given product, color, and size");
    }

    ProductInventory productInventory = productInventoryOpt.get();

    // Cập nhật số lượng
    productInventory.setQuantity(request.getQuantity());
    productInventoryRepository.save(productInventory);
    // Trả về ResponseEntity với dữ liệu cập nhật và mã HTTP 200 OK
    return ResponseEntity.ok(productInventory);
}
    



    

    // Endpoint to delete ProductInventory
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductInventory(@PathVariable("id") Long id) {
        productInventoryService.deleteProductInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductInventory>> getAllProductInventory(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "100") int size
    ) {
         Pageable pageable = PageRequest.of(page, size);
         Page<ProductInventory> productInventories = productInventoryService.getAllProductInventory(pageable);
        return new ResponseEntity<>(productInventories, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductInventory> getProductById(@PathVariable("id") Long productId) {
        ProductInventory productInventory = productInventoryService.getProductById(productId);
        if (productInventory != null) {
            return ResponseEntity.ok(productInventory); // Return 200 OK with the product inventory object
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if product inventory with given ID is not found
        }
    }



    @GetMapping("/product/{productId}")
    public List<ProductInventory> getProductInventoryByProductId(@PathVariable Long productId) {
        return productInventoryService.getProductInventoryByProductId(productId);
    }
    
}