package com.btl.oop.dto.CartItemRequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)

public class CartItemCreationRequest {
    @NotNull
    String userId;

    @NotNull
    Long bookId;

    @NotNull
    Integer quantity;
}
