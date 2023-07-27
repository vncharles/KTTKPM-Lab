package com.charles.productservice.service;

import com.charles.productservice.dto.ProductRequest;
import com.charles.productservice.entity.Product;
import com.charles.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void create(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();

        productRepository.save(product);
    }

    public Product getDetail(Long id) {
        return productRepository.findById(id).get();
    }
}
