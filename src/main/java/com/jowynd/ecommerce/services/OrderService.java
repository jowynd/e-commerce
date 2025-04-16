package com.jowynd.ecommerce.services;

import com.jowynd.ecommerce.domain.Product;
import com.jowynd.ecommerce.domain.user.User;
import com.jowynd.ecommerce.domain.order.Order;
import com.jowynd.ecommerce.domain.order.OrderItem;
import com.jowynd.ecommerce.domain.order.OrderStatus;
import com.jowynd.ecommerce.dto.order.*;
import com.jowynd.ecommerce.dto.product.ProductInfoDTO;
import com.jowynd.ecommerce.dto.user.UserInfoDTO;
import com.jowynd.ecommerce.repositories.OrderItemRepository;
import com.jowynd.ecommerce.repositories.OrderRepository;
import com.jowynd.ecommerce.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

    public void addItemToOrder(Long id, List<OrderItemCreateDTO> itemsDTO) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        for (OrderItemCreateDTO itemDTO : itemsDTO) {
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new RuntimeException());

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);

            if (!product.isActive()){
                throw new RuntimeException("Impossible to add this product, it's inactive!");
            }

            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setUnitPrice(product.getPrice());

            order.getOrderItem().add(orderItem);
        }

        orderRepository.save(order);
    }

    public OrderResponseWithItemsDTO fullOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        return new OrderResponseWithItemsDTO(
                order.getId(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                new UserInfoDTO(
                        order.getUser().getId(),
                        order.getUser().getUsername(),
                        order.getUser().isActive()),
                order.getOrderItem().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getQuantity(),
                                orderItem.getUnitPrice(),
                                new ProductInfoDTO(
                                        orderItem.getProduct().getId(),
                                        orderItem.getProduct().getProductName(),
                                        orderItem.getProduct().isActive()

                                ))
                        ).toList());
    }

    public List<OrderResponseDTO> findAllOrders() {

        List<Order> list = orderRepository.findAll();

        return list.stream().map(order -> new OrderResponseDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderStatus(),
                new UserInfoDTO(
                        order.getUser().getId(),
                        order.getUser().getUsername(),
                        order.getUser().isActive())
        )).collect(Collectors.toList());
    }

     public OrderResponseDTO findOrderById(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        return new OrderResponseDTO(
                order.get().getId(),
                order.get().getTotalPrice(),
                order.get().getOrderStatus(),
                new UserInfoDTO(
                        order.get().getUser().getId(),
                        order.get().getUser().getUsername(),
                        order.get().getUser().isActive()));
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
