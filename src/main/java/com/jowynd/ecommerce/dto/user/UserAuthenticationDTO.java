package com.jowynd.ecommerce.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
