package com.jowynd.ecommerce.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(

        Long id,

        @NotBlank
        String productName,

        @NotBlank
        String description,

        @NotNull
        double price,

        @NotNull
        int quantity) {
}
