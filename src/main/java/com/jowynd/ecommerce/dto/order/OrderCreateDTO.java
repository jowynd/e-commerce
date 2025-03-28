package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;
import com.jowynd.ecommerce.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotNull;

public record OrderCreateDTO(

        Long id,

        @NotNull
        double totalPrice,

        OrderStatus orderStatus,
        UserInfoDTO userInfoDTO
) {
}
