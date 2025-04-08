package com.jowynd.ecommerce.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(

        Long id,

        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password,

        boolean active) {
}
