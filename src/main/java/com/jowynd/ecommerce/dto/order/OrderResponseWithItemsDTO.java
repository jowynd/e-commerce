package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderResponseWithItemsDTO(

        @NotNull
        Long id,

        @NotBlank
        OrderStatus status,

        @NotNull
        double totalPrice,

        List<OrderItemDTO> items
) {
}
