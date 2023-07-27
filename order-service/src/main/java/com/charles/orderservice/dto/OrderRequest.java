package com.charles.orderservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRequest {
    private Long productId;
    private String buyerName;
    private Integer quantity;
    private Date dateBuy;
}
