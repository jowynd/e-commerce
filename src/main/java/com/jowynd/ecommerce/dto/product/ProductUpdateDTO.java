package com.jowynd.ecommerce.dto.product;

public record ProductUpdateDTO(String productName, String description, double price, int quantity) {
}
