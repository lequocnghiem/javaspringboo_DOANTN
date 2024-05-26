package com.api.thuctaptotnghiepbackend.Controller.Cart;

import java.util.List;

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

import com.api.thuctaptotnghiepbackend.Entity.Cart;
import com.api.thuctaptotnghiepbackend.Entity.Product;
import com.api.thuctaptotnghiepbackend.Service.CartService;

import com.api.thuctaptotnghiepbackend.Service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class CartController {
      private  CartService cartService ;
        private ProductService productService;


      
    

        @PostMapping("/addCart")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart savecart = cartService.addCart(cart);
        return new ResponseEntity<>(savecart, HttpStatus.CREATED);
    }



        @GetMapping("/total")
        public ResponseEntity<List<Object>> getCartTotal(@RequestParam List<Long> cartIds) {
            List<Object> result = cartService.calculateTotal(cartIds);
    
            if (!result.isEmpty()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @GetMapping("{userId}")
        public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable("userId") Long userId) {
            List<Cart> userCart = cartService.getCartByUserId(userId);
        
            return ResponseEntity.ok(userCart); // Trả về danh sách giỏ hàng, có thể là rỗng
        
            /*
            // Nếu muốn trả về 404 nếu giỏ hàng trống
            if (!userCart.isEmpty()) {
                return ResponseEntity.ok(userCart);
            } else {
                return ResponseEntity.notFound().build();
            }
            */
        }


        @GetMapping("count/{userId}")
        public ResponseEntity<Integer> getCartItemCountByUserId(@PathVariable("userId") Long userId) {
            int itemCount = cartService.getCartItemCountByUserId(userId);
        
            // Trả về số lượng sản phẩm trong giỏ hàng
            return ResponseEntity.ok(itemCount);
        
            /*
            // Nếu muốn trả về 404 nếu giỏ hàng trống
            if (itemCount > 0) {
                return ResponseEntity.ok(itemCount);
            } else {
                return ResponseEntity.notFound().build();
            }
            */
        }
        


        @PutMapping("{cartId}")
        public ResponseEntity<String> updateCart(
                @PathVariable("cartId") Long cartId,
                @RequestParam("qty") int qty,
                @RequestParam("color") String color,
                @RequestParam("size") String size) {
        
            cartService.updateCart(cartId, qty, color, size);
            return ResponseEntity.ok("Cart updated successfully");
        }


        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteCart(@PathVariable("id") Long id) {
            cartService.remove(id);
            return new ResponseEntity<>("Cart successfully deleted!", HttpStatus.OK);
        }

}
