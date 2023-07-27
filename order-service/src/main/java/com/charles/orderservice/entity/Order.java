package com.charles.orderservice.entity;

import com.charles.orderservice.dto.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @Table(name = "t_order")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long product;
    private String buyerName;
    private Integer quantity;
    private Date dateBuy;

}
