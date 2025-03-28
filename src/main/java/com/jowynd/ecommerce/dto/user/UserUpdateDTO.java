package com.jowynd.ecommerce.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(

        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password) {
}
