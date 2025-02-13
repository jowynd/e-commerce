package com.jowynd.ecommerce.dto.product;

public record ProductDTO(Long id, String productName, String description, double price, int quantity) {
}
