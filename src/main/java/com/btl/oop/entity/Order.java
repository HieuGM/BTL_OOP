package com.btl.oop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userId;
//    Long totalAmount = 0l;
    LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<OrderItem> items = new ArrayList<>();

    // Helper method để đảm bảo quan hệ hai chiều
    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);  // ⭐ Thiết lập quan hệ ngược
    }

    public void removeOrderItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }
//    @PrePersist @PreUpdate
//    public void calcTotalAmount() {
//        this.totalAmount = this.items.stream()
//                .mapToLong(OrderItem::getTotalPrice)
//                .sum();
//    }
}

