package com.jowynd.ecommerce.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInfoDTO(

    @NotNull
    Long id,

    @NotBlank
    String username) {
}
