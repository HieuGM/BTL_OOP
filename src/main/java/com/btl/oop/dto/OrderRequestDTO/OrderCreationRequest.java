package com.btl.oop.dto.OrderRequestDTO;

import com.btl.oop.entity.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderCreationRequest {
    @NotNull
    String userId;
    @NotEmpty
    List<OrderItemRequest> items;

}

