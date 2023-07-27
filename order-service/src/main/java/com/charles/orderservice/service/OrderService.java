package com.charles.orderservice.service;


import com.charles.orderservice.dto.OrderRequest;
import com.charles.orderservice.dto.Product;
import com.charles.orderservice.entity.Order;
import com.charles.orderservice.repository.OrderRepository;
import com.charles.orderservice.utils.ProductNotFoundException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final WebClient webClient;
    private final OrderRepository orderRepository;

    @Retry(name = "productServiceRetry", fallbackMethod = "handleProductServiceFailure")
    public void createOrder(OrderRequest request) throws ProductNotFoundException {
        Order order = Order.builder()
                .dateBuy(request.getDateBuy())
                .buyerName(request.getBuyerName())
                .quantity(request.getQuantity())
                .build();

        String productServiceUrl = "http://localhost:8081/api/product/" + request.getProductId();
        Product product = webClient.get()
                .uri(productServiceUrl)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        if (product == null) {
            throw new ProductNotFoundException("Product not found with ID: " + request.getProductId());
        } else {
            System.out.println("Product is OK");
            order.setProduct(product.getId());
            orderRepository.save(order);
        }
    }

    // Fallback method for handling product service failures
    private Product handleProductServiceFailure(String productId, Throwable throwable) {
        // Log the error for debugging purposes
        System.out.println("Product service call failed for productId: " + productId);
        throwable.printStackTrace();

        // Return a default product or take appropriate fallback action
        // For example:
        // return new Product("defaultProductId", "Default Product", 0.0);
        return null;
    }
}
