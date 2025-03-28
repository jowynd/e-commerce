package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.dto.product.ProductInfoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemDTO(

        @NotNull
        Long id,

        @NotNull
        int quantity,

        @NotNull
        double unityPrice,

        ProductInfoDTO productInfoDTO
) {
}
