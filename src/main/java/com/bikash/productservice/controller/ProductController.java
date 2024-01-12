package com.bikash.productservice.controller;

import com.bikash.productservice.dto.ProductRequest;
import com.bikash.productservice.dto.ProductResponse;
import com.bikash.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    @PostMapping("/create/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest)
    {
        productService.createProduct(productRequest);
    }

    @PutMapping("/update/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest)
    {
        productService.updateProduct(productId, productRequest);
    }
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }
}
