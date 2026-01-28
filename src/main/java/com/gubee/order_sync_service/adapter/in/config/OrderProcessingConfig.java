package com.gubee.order_sync_service.adapter.in.config;

import com.gubee.order_sync_service.application.OrderProcessingService;
import com.gubee.order_sync_service.domain.port.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessingConfig {

    @Bean
    public OrderProcessingService orderProcessingService(
            OrderRepository orderRepository
    ) {
        return new OrderProcessingService(orderRepository);
    }
}
