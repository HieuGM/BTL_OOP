package com.btl.oop.controller;

import com.btl.oop.dto.ApiResponse;
import com.btl.oop.dto.OrderRequestDTO.OrderCreationRequest;
import com.btl.oop.entity.Order;
import com.btl.oop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    ApiResponse<Order> createOrder(@RequestBody @Valid OrderCreationRequest request) {
        ApiResponse<Order> response = new ApiResponse<>();
        response.setResult(orderService.createOrder(request));
        return response;
    }

    @GetMapping
    List<Order> getOrder() {
        return orderService.getOrders();
    }
}
