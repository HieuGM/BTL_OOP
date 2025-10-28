package com.btl.oop.service;

import com.btl.oop.dto.OrderRequestDTO.OrderCreationRequest;
import com.btl.oop.entity.Order;
import com.btl.oop.entity.OrderItem;
import com.btl.oop.entity.OrderStatus;
import com.btl.oop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderCreationRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        // Convert OrderItemRequest -> OrderItem
        // Tạo OrderItem từ request
        request.getItems().forEach(itemReq -> {
            OrderItem item = new OrderItem();
            item.setBookId(itemReq.getBookId());
            item.setQuantity(itemReq.getQuantity());
//            item.setPrice(itemReq.getPrice());

            order.addOrderItem(item); // ⭐ Quan trọng nhất
        });


        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
