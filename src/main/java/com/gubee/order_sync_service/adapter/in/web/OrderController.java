package com.gubee.order_sync_service.adapter.in.web;

import com.gubee.order_sync_service.application.OrderProcessingService;

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
    public ResponseEntity<Void> createOrder(
        @RequestParam String id,
        @RequestParam String marketplace
    ) {
        service.createAndStartProcessing(id, marketplace);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    
}
