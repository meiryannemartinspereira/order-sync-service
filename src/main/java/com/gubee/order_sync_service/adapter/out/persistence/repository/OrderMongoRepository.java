package com.gubee.order_sync_service.adapter.out.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gubee.order_sync_service.adapter.out.persistence.document.OrderDocument;

public interface OrderMongoRepository
        extends MongoRepository<OrderDocument, String> {
}
