package com.gubee.order_sync_service.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void shouldCreateOrderWithStatusCreated(){
        OrderId orderId = OrderId.generate();

        Order order = new Order(orderId, "MERCADO_LIVRE");

        assertEquals(OrderStatus.CREATED, order.getStatus());

    }

    @Test
    void shouldStartProcessingWhenOrderIsCreated() {
        OrderId orderId = OrderId.generate();
        Order order = new Order(orderId, "MERCADO_LIVRE");

        order.startProcessing();
        
        assertEquals(OrderStatus.PROCESSING, order.getStatus());
    }

    @Test
    void shouldNotStartProcessingIfOrderIsNotCreated(){
        OrderId orderId = OrderId.generate();
        Order order = new Order(orderId, "MERCADO_LIVRE");
        order.startProcessing();

        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            order::startProcessing
        );

        assertEquals("Order cannot start processing", exception.getMessage());
    }
    
}
