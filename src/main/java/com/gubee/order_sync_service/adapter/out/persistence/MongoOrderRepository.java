package com.gubee.order_sync_service.adapter.out.persistence;

import com.gubee.order_sync_service.adapter.out.persistence.document.OrderDocument;
import com.gubee.order_sync_service.adapter.out.persistence.repository.OrderMongoRepository;
import com.gubee.order_sync_service.domain.model.Order;
import com.gubee.order_sync_service.domain.port.OrderRepository;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MongoOrderRepository implements OrderRepository{

    private final OrderMongoRepository mongoRepository;

    public MongoOrderRepository(OrderMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public void save(Order order) {
        OrderDocument document = OrderDocument.from(order);
        mongoRepository.save(document);
    }
    
    @Override
    public Optional<Order> findById(String id) {
        return mongoRepository.findById(id)
        .map(OrderDocument::toDomain);
    }
}
