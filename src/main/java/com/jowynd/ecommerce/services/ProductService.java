package com.jowynd.ecommerce.services;

import com.jowynd.ecommerce.domain.Product;
import com.jowynd.ecommerce.dto.product.ProductDTO;
import com.jowynd.ecommerce.dto.product.ProductUpdateDTO;
import com.jowynd.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
                product.getQuantity(),
                product.isActive(),
                product.getImage());
    }

    public List<ProductDTO> getAllProducts() {

        List<Product> list = repository.findAll();

        return list.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.isActive(),
                        product.getImage()
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

    public void turnInactive(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This product does not exist!"));

        product.setActive(false);

        repository.save(product);
    }

    public void turnActive(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("This product does not exist!"));

        product.setActive(true);

        repository.save(product);
    }

    public void uploadImage(Long id, MultipartFile file) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        try {
            product.setImage(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        repository.save(product);
    }

    public byte[] getImageByProductId(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getImage() != null) {
            return product.getImage();
        } else {
            return "image not found".getBytes();
        }
    }
}