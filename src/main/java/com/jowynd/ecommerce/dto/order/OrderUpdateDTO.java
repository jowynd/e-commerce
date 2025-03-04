package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;

public record OrderUpdateDTO(
        OrderStatus orderStatus,
        double totalPrice
) {
}
