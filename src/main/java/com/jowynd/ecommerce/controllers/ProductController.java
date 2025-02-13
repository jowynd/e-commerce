package com.jowynd.ecommerce.controllers;

import com.jowynd.ecommerce.domain.Product;
import com.jowynd.ecommerce.dto.product.ProductDTO;
import com.jowynd.ecommerce.dto.product.ProductUpdateDTO;
import com.jowynd.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductDTO product) {
        service.addProduct(product);

        return ResponseEntity.ok().body("Created with success!");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getProductById(@PathVariable @Valid Long id) {

        ProductDTO dto = service.getProductById(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        List<ProductDTO> list = service.getAllProducts();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO dto) {
        Product product = service.updateProduct(id, dto);

        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
