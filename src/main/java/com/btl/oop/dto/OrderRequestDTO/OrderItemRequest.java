package com.btl.oop.dto.OrderRequestDTO;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderItemRequest {
    Long bookId;
    Long quantity;
}