package com.jowynd.ecommerce.services;

import com.jowynd.ecommerce.domain.User;
import com.jowynd.ecommerce.domain.order.Order;
import com.jowynd.ecommerce.domain.order.OrderStatus;
import com.jowynd.ecommerce.dto.order.OrderCreateDTO;
import com.jowynd.ecommerce.dto.order.OrderResponseDTO;
import com.jowynd.ecommerce.dto.order.OrderUpdateDTO;
import com.jowynd.ecommerce.dto.user.UserInfoDTO;
import com.jowynd.ecommerce.repositories.OrderRepository;
import com.jowynd.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    public OrderResponseDTO AddOrder(OrderCreateDTO dto) {

        User user = userRepository.findById(dto.userInfoDTO().id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setTotalPrice(order.getTotalPrice());
        order.setOrderStatus(OrderStatus.PREPARING);
        order.setUser(user);
        
        orderRepository.save(order);
        
        return new OrderResponseDTO(dto.id(), dto.totalPrice(), dto.orderStatus(), dto.userInfoDTO());
    }

    public List<OrderResponseDTO> findAllOrders() {

        List<Order> list = orderRepository.findAll();

        return list.stream().map(order -> new OrderResponseDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                new UserInfoDTO(order.getUser().getId(), order.getUser().getUsername())
        )).collect(Collectors.toList());
    }

    public OrderResponseDTO findOrderById(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        return new OrderResponseDTO(
                order.get().getId(),
                order.get().getTotalPrice(),
                order.get().getOrderStatus(),
                new UserInfoDTO(order.get().getUser().getId(),
                order.get().getUser().getUsername()));
    }

    public Order updateOrder(Long id, OrderUpdateDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(dto.orderStatus());
        order.setTotalPrice(dto.totalPrice());

        return orderRepository.save(order);

    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
