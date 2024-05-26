package com.api.thuctaptotnghiepbackend.Service;

import java.util.List;

import com.api.thuctaptotnghiepbackend.Entity.Cart;

public interface CartService {
    Cart addCart(Cart cart);
    void updateCart(Long cartId, int qty, String color, String size);
    void remove(Long id);
    List<Cart> getCartByUserId(Long userId);
    List<Object> calculateTotal(List<Long> cartIds);
    int getCartItemCountByUserId(Long userId);
}
