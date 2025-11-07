package com.btl.oop.service;

import com.btl.oop.dto.CartItemRequestDTO.CartItemCreationRequest;
import com.btl.oop.entity.CartItem;

import java.util.List;

public interface CartItemService {
    public CartItem addCartItem(CartItemCreationRequest request);
    public List<CartItem> getCartItems();
}
