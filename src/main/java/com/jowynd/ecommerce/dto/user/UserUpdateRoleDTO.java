package com.jowynd.ecommerce.dto.user;

import com.jowynd.ecommerce.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRoleDTO(

        UserRole userRole
) {
}
