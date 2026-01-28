package com.gubee.order_sync_service.adapter.out.persistence.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gubee.order_sync_service.domain.model.Order;

@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String id;
    private String marketplace;
    private String status;

    protected OrderDocument() {}

    public OrderDocument(String id, String marketplace, String status) {
        this.id = id;
        this.marketplace = marketplace;
        this.status = status;
    }

    public static OrderDocument from(Order order ) {
        return new OrderDocument(
            order.getId(),
            order.getMarketplace(),
            order.getStatus().name()
        );

    }

    public Order toDomain() {
        Order order = new Order(id, marketplace);
        return order;
    }
}
