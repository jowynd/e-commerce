package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;
import com.jowynd.ecommerce.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderResponseDTO(

        @NotNull
        Long id,

        @NotNull
        double totalPrice,

        @NotBlank
        OrderStatus orderStatus,

        @NotBlank
        UserInfoDTO userInfoDTO
) {
}
