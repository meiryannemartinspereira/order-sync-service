package com.gubee.order_sync_service.domain.port;

import com.gubee.order_sync_service.domain.model.Order;


import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order>findById(String id);
    
}
