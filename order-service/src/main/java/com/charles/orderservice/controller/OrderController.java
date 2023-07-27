package com.charles.orderservice.controller;

import com.charles.orderservice.dto.OrderRequest;
import com.charles.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest request) throws Exception {

        orderService.createOrder(request);

        return "Create order is successfully!";
    }
}
