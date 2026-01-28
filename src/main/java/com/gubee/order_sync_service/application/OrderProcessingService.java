package com.gubee.order_sync_service.application;

import com.gubee.order_sync_service.domain.model.Order;
import com.gubee.order_sync_service.domain.port.OrderRepository;


public class OrderProcessingService {

    private final OrderRepository orderRepository;

    public OrderProcessingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createAndStartProcessing(String orderId, String marketplace) {
        Order order = new Order(orderId, marketplace);
        order.startProcessing();

        orderRepository.save(order);

        return order;
    }
}
