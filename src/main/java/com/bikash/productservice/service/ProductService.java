package com.bikash.productservice.service;

import com.bikash.productservice.dto.ProductRequest;
import com.bikash.productservice.dto.ProductResponse;
import com.bikash.productservice.model.Product;
import com.bikash.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository; // productRepository variable

    public void createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                        .name(productRequest.getName())
                                .description(productRequest.getDescription())
                                        .price(productRequest.getPrice())
                .build();
       productRepository.save(product);
       log.info("Product {} is saved!", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                        .name(product.getName())
                                .description(product.getDescription())
                                        .price(product.getPrice())
                .build();
    }

    public void updateProduct(String productId, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent())
        {
            // Update the existing product's properties
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());

            // Save the updated product back to the database
            productRepository.save(existingProduct);

            log.info("Product with ID {} is updated!", existingProduct.getId());
        } else {
            log.warn("Product with ID {} not found.", productId);
            // Handle the case where the product with the given ID is not found
            // You might throw an exception, return a specific response, etc.
            // Throw a NoSuchElementException indicating that the product is not found
            throw new NoSuchElementException("Could not find a product with the given ID!");
        }
    }
}
