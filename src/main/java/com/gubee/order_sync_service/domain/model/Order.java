package com.gubee.order_sync_service.domain.model;

import java.util.Objects;

public class Order {

    private final String id;
    private final String marketplace;
    private OrderStatus status;

    public Order(String id, String marketplace){
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("Order id is required");
        }

        if (marketplace == null || marketplace.isBlank()){
            throw new IllegalArgumentException("Marketplace is required");
        }

        this.id = id;
        this.marketplace = marketplace;
        this.status = OrderStatus.CREATED;
    }

    public void startProcessing(){
        if (status != OrderStatus.CREATED){
            throw new IllegalStateException("Order cannot start processing");
        }
        this.status = OrderStatus.PROCESSING;
    }

    public void finishProcessing() {
        if (status != OrderStatus.PROCESSING) {
            throw new IllegalStateException("Order is not processing");
        }

        this.status = OrderStatus.PROCESSED;
    }

    public void fail() {
        this.status = OrderStatus.FAILED;
    }
    
    public String getId() {
        return id;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public OrderStatus getStatus(){
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if(!(o instanceof Order order)) return false;
        return id.equals((order.id));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
