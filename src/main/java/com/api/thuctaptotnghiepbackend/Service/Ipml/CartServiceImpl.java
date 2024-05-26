package com.api.thuctaptotnghiepbackend.Service.Ipml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;


import com.api.thuctaptotnghiepbackend.Entity.Cart;
import com.api.thuctaptotnghiepbackend.Repository.Cart.CartReepository;
import com.api.thuctaptotnghiepbackend.Service.CartService;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    
   private  CartReepository cartRepository;
   

   

    @Override
    public Cart addCart(Cart cart) {
     
        Cart existingCart = cartRepository.findByProductIdAndUserIdAndSizeAndColor(
            cart.getProduct().getId(), 
            cart.getUser().getId(),
            cart.getSize(), 
            cart.getColor()
        );
    
        if (existingCart != null) {
            // Nếu tồn tại cart, cập nhật số lượng
            existingCart.setQty(existingCart.getQty() + cart.getQty());
            return cartRepository.save(existingCart);
        } else {
           
            return cartRepository.save(cart);
        }
    }



  @Override
   public void remove(Long id) {
       // Check if the cart already exists based on some criteria (e.g., productId)
       cartRepository.deleteById(id);
   }


   @Override
public void updateCart(Long cartId, int qty, String color, String size) {
    Optional<Cart> optionalCart = cartRepository.findById(cartId);
    if (optionalCart.isPresent()) {
        Cart cart = optionalCart.get();
        cart.setQty(qty);
        cart.setColor(color);
        cart.setSize(size);
        cartRepository.save(cart);
    } 
}




   @Override
   public int getCartItemCountByUserId(Long userId) {
       List<Cart> userCarts = cartRepository.findByUserId(userId);
       return userCarts.size();
   }
   


 @Override
    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

   @Override
public List<Object> calculateTotal(List<Long> cartIds) {
    double total = 0.0;
    List<Object> allCartItems = new ArrayList<>();

    for (Long cartId : cartIds) {
        System.out.println("Processing cart with ID: " + cartId);

        Optional<Cart> cart = cartRepository.findById(cartId);

        if (cart.isPresent()) {
            System.out.println("Cart " + cart.get().getId());

            double cartTotal = cart.stream()
                    .mapToDouble(item -> {
                        double itemTotal = item.getPrice() * item.getQty();
                        List<Object> itemDetails = new ArrayList<>();
                        itemDetails.add(item.getProduct().getId()); // Đổi từ getProductId() thành getProduct().getId()
                 
                        itemDetails.add(item.getPrice());
                        itemDetails.add(item.getQty());
                        itemDetails.add(item.getSize());
                        allCartItems.add(itemDetails);
                        return itemTotal;
                    })
                    .sum();

            System.out.println("Total for cart " + cartId + ": " + cartTotal);

            total += cartTotal;
        } else {
            System.out.println("Cart with ID " + cartId + " not found.");
        }
    }

    System.out.println("Final total: " + total);

    List<Object> result = new ArrayList<>();
    result.add(total);
    result.add(allCartItems);

    return result;
}








}
