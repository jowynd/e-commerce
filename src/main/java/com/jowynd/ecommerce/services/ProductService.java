package com.jowynd.ecommerce.services;

import com.jowynd.ecommerce.domain.Product;
import com.jowynd.ecommerce.dto.ProductDTO;
import com.jowynd.ecommerce.dto.product.ProductUpdateDTO;
import com.jowynd.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void addProduct(ProductDTO dto) {

        Product product = new Product();
        product.setProductName(dto.productName());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        repository.save(product);
    }

    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found"));

        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity());
    }

    public List<ProductDTO> getAllProducts() {

        List<Product> list = repository.findAll();

        return list.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity()
                )).collect(Collectors.toList());
    }

    public Product updateProduct(Long id, ProductUpdateDTO dto) {

        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product product = optionalProduct.get();
        product.setProductName(dto.productName());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        return repository.save(product);
    }

    public void deleteById (Long id) {
        repository.deleteById(id);
    }
}
