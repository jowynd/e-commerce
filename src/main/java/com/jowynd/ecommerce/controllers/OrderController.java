package com.jowynd.ecommerce.controllers;

import com.jowynd.ecommerce.domain.order.Order;
import com.jowynd.ecommerce.dto.order.*;
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

    @PutMapping(value = "/{id}/item")
    public ResponseEntity addItemToOrder(@PathVariable @Valid Long id, @RequestBody List<OrderItemCreateDTO> dto) {

        orderService.addItemToOrder(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity findAllOrders() {

        List<OrderResponseDTO> list = orderService.findAllOrders();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}/test")
    public ResponseEntity findFullOrderById(@PathVariable @Valid Long id) {

        OrderResponseWithItemsDTO order = orderService.fullOrder(id);

        return ResponseEntity.ok().body(order);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity findOrderById(@PathVariable @Valid Long id) {

        OrderResponseDTO order = orderService.findOrderById(id);

        return ResponseEntity.ok().body(order);
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
