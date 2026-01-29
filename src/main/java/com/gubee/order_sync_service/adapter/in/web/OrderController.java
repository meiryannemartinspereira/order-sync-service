package com.gubee.order_sync_service.adapter.in.web;

import com.gubee.order_sync_service.adapter.in.web.dto.CreateOrderRequest;
import com.gubee.order_sync_service.application.OrderProcessingService;
import com.gubee.order_sync_service.domain.model.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProcessingService service;

    public OrderController(OrderProcessingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
        @RequestBody CreateOrderRequest request
    ) {
        Order order = service.createAndStartProcessing(request.getMarketplace());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    
}
