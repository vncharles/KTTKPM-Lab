package com.charles.productservice.controller;

import com.charles.productservice.dto.ProductRequest;
import com.charles.productservice.entity.Product;
import com.charles.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        productService.create(request);
    }

    @GetMapping("/{id}")
    public Product getDetail(@PathVariable("id")Long id) {
        return productService.getDetail(id);
    }
}
