package com.api.thuctaptotnghiepbackend.Service.Ipml;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Entity.ProductColor;
import com.api.thuctaptotnghiepbackend.Entity.ProductSize;
import com.api.thuctaptotnghiepbackend.Entity.Productoption;
import com.api.thuctaptotnghiepbackend.Entity.Productoptionvalue;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductRepository;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionvalueRepository;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductcolorRepository;
import com.api.thuctaptotnghiepbackend.Repository.ProductColorandSize.ProductsizeRepository;
import com.api.thuctaptotnghiepbackend.Service.ProductService;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.api.thuctaptotnghiepbackend.Repository.Product.ProductoptionRepository;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductoptionvalueRepository  productoptionvalueRepository;


    private ProductcolorRepository  productColorRepository;

    private ProductoptionRepository  ProductoptionRepository;
    private ProductsizeRepository  productsizeRepository;
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
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
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setBrand(product.getBrand());;
            updatedProduct.setMetakey(product.getMetakey());
            updatedProduct.setMetadesc(product.getMetadesc());
            updatedProduct.setCreatedAt(product.getCreatedAt());
            updatedProduct.setUpdatedAt(product.getUpdatedAt());
            updatedProduct.setColors(product.getColors());
            updatedProduct.setSizes(product.getSizes());
            updatedProduct.setStatus(product.getStatus());

            return productRepository.save(updatedProduct);
        } else {
            // Xử lý khi sản phẩm không tồn tại
            return null;
        }
    }
   

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    @Override
    public List<Product> searchProducts(String searchKeyword) {
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        List<Product> productList = productRepository.findAll();
    
        // Chuyển đổi từ khóa tìm kiếm sang chữ thường để so sánh không phân biệt chữ hoa chữ thường
        String searchKeyLower = searchKeyword.toLowerCase();
    
        // Lọc danh sách sản phẩm dựa trên từ khóa tìm kiếm
        List<Product> searchResults = productList.stream()
                .filter(product ->
                        product.getName().toLowerCase().contains(searchKeyLower) ||
                        product.getMetakey().toLowerCase().contains(searchKeyLower) ||
                        product.getMetadesc().toLowerCase().contains(searchKeyLower)||
                        product.getBrand().getName().toLowerCase().contains(searchKeyLower)||
                        product.getBrand().getMetadesc().toLowerCase().contains(searchKeyLower)||
                        product.getBrand().getMetakey().toLowerCase().contains(searchKeyLower)||
                        product.getCategory().getName().toLowerCase().contains(searchKeyLower)||
                        product.getCategory().getMetadesc().toLowerCase().contains(searchKeyLower)||
                        product.getCategory().getMetakey().toLowerCase().contains(searchKeyLower)
                        
                )
                .collect(Collectors.toList());
    
        return searchResults;
    }





    @Override
    public void addColorsToProduct(Long productId, List<Long> colorIds, List<Long> sizeIds ) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            
            // Kiểm tra và khởi tạo trường colors nếu nó là null
            if (product.getColors() == null) {
                product.setColors(new HashSet<>());
            }
            
            // Kiểm tra và khởi tạo trường sizes nếu nó là null
            if (product.getSizes() == null) {
                product.setSizes(new HashSet<>());
            }
            
            // Thêm màu vào sản phẩm
            for (Long colorId : colorIds) {
                Optional<ProductColor> optionalColor = productColorRepository.findById(colorId);
                if (optionalColor.isPresent()) {
                    product.getColors().add(optionalColor.get());
                } else {
                    System.out.println("Color with id " + colorId + " does not exist.");
                }
            }
            // Thêm size vào sản phẩm
            for (Long sizeId : sizeIds) {
                Optional<ProductSize> optionalSize = productsizeRepository.findById(sizeId);
                if (optionalSize.isPresent()) {
                    product.getSizes().add(optionalSize.get());
                } else {
                    System.out.println("Size with id " + sizeId + " does not exist.");
                }
            }
            
            // Lưu sản phẩm sau khi đã thêm màu và size
            productRepository.save(product);
        } else {
            System.out.println("Product with id " + productId + " does not exist.");
        }
    }

    








    @Override
    public void updateColorsOfProduct(Long productId, List<Long> newColorIds,List<Long> newSizeIds) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            
            // Lấy danh sách màu cũ của sản phẩm
            Set<ProductColor> oldColors = product.getColors();
            
            // Xóa các màu cũ không cần thiết
            for (ProductColor oldColor : oldColors) {
                if (!newColorIds.contains(oldColor.getId())) {
                    product.getColors().remove(oldColor);
                }
            }
            
            // Thêm các màu mới
            for (Long newColorId : newColorIds) {
                Optional<ProductColor> optionalNewColor = productColorRepository.findById(newColorId);
                if (optionalNewColor.isPresent()) {
                    product.getColors().add(optionalNewColor.get());
                } else {
                    System.out.println("Color with id " + newColorId + " does not exist.");
                }
            }





            Set<ProductSize> oldSizes = product.getSizes();
        
            // Xóa các kích thước cũ không cần thiết
            for (ProductSize oldSize : oldSizes) {
                if (!newSizeIds.contains(oldSize.getId())) {
                    product.getSizes().remove(oldSize);
                }
            }
            
            // Thêm các kích thước mới
            for (Long newSizeId : newSizeIds) {
                Optional<ProductSize> optionalNewSize = productsizeRepository.findById(newSizeId);
                if (optionalNewSize.isPresent()) {
                    product.getSizes().add(optionalNewSize.get());
                } else {
                    System.out.println("Size with id " + newSizeId + " does not exist.");
                }
            }
            
            // Lưu sản phẩm sau khi cập nhật
            productRepository.save(product);
        } else {
            System.out.println("Product with id " + productId + " does not exist.");
        }
    }
    











    @Override
   public List<Product> findProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findBypriceBetween(minPrice, maxPrice);
    }


    @Override
    public List<Product> findProductsByPriceAndValue(Double minPrice, Double maxPrice, List<String> values) {
      
        if (minPrice == null && maxPrice == null) {
            // Trường hợp không có giá trị hoặc khoảng giá, tìm kiếm theo giá trị
            List<Productoptionvalue> productsByValues = productoptionvalueRepository.findByvalueIn(values);
    
            // Tạo một Map để lưu Productoptionvalue theo productId
            Map<Long, Productoptionvalue> productOptionValueMap = new HashMap<>();
            for (Productoptionvalue productOptionValue : productsByValues) {
                productOptionValueMap.put(productOptionValue.getProduct().getId(), productOptionValue);
            }
    
            List<Product> combinedResults = new ArrayList<>();
            List<Product> allProducts = productRepository.findAll();
    
            // Kết hợp kết quả từ cả hai danh sách
            for (Product product : allProducts) {
                // Kiểm tra xem có Productoptionvalue tương ứng không
                Productoptionvalue productOptionValue = productOptionValueMap.get(product.getId());
                if (productOptionValue != null) {
                    // Có sản phẩm tương ứng, thêm vào danh sách kết quả
                    combinedResults.add(product);
                }
            }
    
            // In ra kết quả để kiểm tra
            System.out.println("size " + combinedResults);
            return combinedResults;
        }else if(values == null || values.isEmpty()) {
            List<Product> productsByPrice = productRepository.findBypriceBetween(minPrice, maxPrice);
            System.out.println("Results by Price Only: " + productsByPrice);
            return productsByPrice;
        }
        
        
        
        else if (minPrice != null && maxPrice != null && values !=null) {
            // Trường hợp cả hai giá trị đều được chỉ định, tìm kiếm theo cả giá và giá trị
            List<Productoptionvalue> productsByValues = productoptionvalueRepository.findByvalueIn(values);
            List<Product> productsByPrice = productRepository.findBypriceBetween(minPrice, maxPrice);
    
            // Tạo một Map để lưu Productoptionvalue theo productId
            Map<Long, Productoptionvalue> productOptionValueMap = new HashMap<>();
            for (Productoptionvalue productOptionValue : productsByValues) {
                productOptionValueMap.put(productOptionValue.getProductoption().getId(), productOptionValue);
            }
    
            List<Product> combinedResults = new ArrayList<>();
    
            // Kết hợp kết quả từ cả hai danh sách
            for (Product product : productsByPrice) {
                // Kiểm tra xem có Productoptionvalue tương ứng không
                Productoptionvalue productOptionValue = productOptionValueMap.get(product.getId());
                if (productOptionValue != null) {
                    // Có sản phẩm tương ứng, thêm vào danh sách kết quả
                    combinedResults.add(product);
                }
            }
    
            // In ra kết quả để kiểm tra
            System.out.println("ca 2  " + combinedResults);
            return combinedResults;
        } else{
            return null;
        }
           
         
        
    }
    
    
    
    public Map<String, List<Map<String, String>>> getProductOptionsAndValues(Long productId) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
    
        // Tìm kiếm tất cả ProductOption của Product
        List<Productoption> productOptions = ProductoptionRepository.findByproductId(productId);
    
        // Duyệt qua từng ProductOption
        for (Productoption productOption : productOptions) {
          System.err.println(productOption.getName());
            List<Productoptionvalue> productOptionValues = productoptionvalueRepository.findByProductoption_IdAndProduct_Id(
                    productOption.getId(),
                    productId
            );
    
            // Nếu có ít nhất một ProductOptionValue
            if (!productOptionValues.isEmpty()) {
                // Lưu danh sách giá trị của tất cả ProductOptionValue
                List<Map<String, String>> optionValues = new ArrayList<>();
    
                for (Productoptionvalue optionValue : productOptionValues) {
                    Map<String, String> valueMap = new HashMap<>();
                    valueMap.put("value", optionValue.getValue());
                    // Nếu có các trường khác trong Productoptionvalue, bạn có thể thêm chúng vào đây
                    // valueMap.put("field_name", optionValue.getFieldName());
                    optionValues.add(valueMap);
                }
    
                // Thêm vào kết quả
                result.put(productOption.getName(), optionValues);
            }
        }
    
        return result;
    }











   

    
}

