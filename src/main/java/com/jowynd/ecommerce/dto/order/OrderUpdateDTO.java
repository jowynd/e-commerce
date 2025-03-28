package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateDTO(

        @NotBlank
        OrderStatus orderStatus,

        @NotNull
        double totalPrice
) {
}
