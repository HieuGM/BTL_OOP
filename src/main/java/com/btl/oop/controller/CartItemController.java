package com.btl.oop.controller;

import com.btl.oop.dto.ApiResponse;
import com.btl.oop.dto.CartItemRequestDTO.CartItemCreationRequest;
import com.btl.oop.entity.CartItem;
import com.btl.oop.service.impl.CartItemServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")

public class CartItemController {
    @Autowired
    private CartItemServiceImpl cartItemService;

    @PostMapping
    ApiResponse<CartItem> createCartItem(@RequestBody @Valid CartItemCreationRequest request) {
        ApiResponse<CartItem> response = new ApiResponse<>();
        response.setResult(cartItemService.addCartItem(request));
        return response;
    }

    @GetMapping
    List<CartItem> getCartItems() {
        return cartItemService.getCartItems();
    }
}
