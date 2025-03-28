package com.jowynd.ecommerce.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductInfoDTO(

        @NotNull
        Long id,

        @NotBlank
        String productName
) {
}
