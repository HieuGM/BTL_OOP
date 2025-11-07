package com.btl.oop.service.impl;

import com.btl.oop.dto.CartItemRequestDTO.CartItemCreationRequest;
import com.btl.oop.entity.CartItem;
import com.btl.oop.repository.CartItemRepository;
import com.btl.oop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(CartItemCreationRequest request) {
        CartItem cartItem = new CartItem();

        cartItem.setBookId(request.getBookId());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setUserId(request.getUserId());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }
}
