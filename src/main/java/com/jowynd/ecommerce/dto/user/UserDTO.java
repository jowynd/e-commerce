package com.jowynd.ecommerce.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(

        Long id,

        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password) {
}
