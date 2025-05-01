package com.jowynd.ecommerce.dto.user;

import com.jowynd.ecommerce.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(

        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password,

        UserRole userRole
) {
}
