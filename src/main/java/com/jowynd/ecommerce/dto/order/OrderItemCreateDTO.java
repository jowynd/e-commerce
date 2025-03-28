package com.jowynd.ecommerce.dto.order;



public record OrderItemCreateDTO(
        Long productId,
        int quantity
) {
}
