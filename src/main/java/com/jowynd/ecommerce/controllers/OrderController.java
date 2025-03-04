package com.jowynd.ecommerce.controllers;

import com.jowynd.ecommerce.dto.order.OrderCreateDTO;import com.jowynd.ecommerce.dto.order.OrderResponseDTO;
import com.jowynd.ecommerce.dto.order.OrderUpdateDTO;
import com.jowynd.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody @Valid OrderCreateDTO order) {

        orderService.AddOrder(order);

        return ResponseEntity.ok().body("Order created with success!");
    }

    @GetMapping
    public ResponseEntity findAllOrders() {

        List<OrderResponseDTO> list = orderService.findAllOrders();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findOrderById(@PathVariable @Valid Long id) {

        OrderResponseDTO response = orderService.findOrderById(id);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateOrderById(@PathVariable @Valid Long id, @RequestBody OrderUpdateDTO dto) {

        orderService.updateOrder(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrderById(@PathVariable @Valid Long id) {

        orderService.deleteOrderById(id);

        return ResponseEntity.ok().body("Deleted with success!");
    }
}
