package com.gubee.order_sync_service.adapter.out.persistence;

import com.gubee.order_sync_service.domain.model.Order;
import com.gubee.order_sync_service.domain.model.OrderId;
import com.gubee.order_sync_service.domain.port.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<OrderId, Order> database = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        database.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
    
}
