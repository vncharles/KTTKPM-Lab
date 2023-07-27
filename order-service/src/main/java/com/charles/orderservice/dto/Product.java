package com.charles.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
