package com.charles.productservice.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
