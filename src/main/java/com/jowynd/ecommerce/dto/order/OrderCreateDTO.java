package com.jowynd.ecommerce.dto.order;

import com.jowynd.ecommerce.domain.order.OrderStatus;
import com.jowynd.ecommerce.dto.user.UserInfoDTO;

public record OrderCreateDTO(

        Long id,

        double totalPrice,

        OrderStatus orderStatus,

        UserInfoDTO userInfoDTO
) {
}
