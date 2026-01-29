package com.gubee.order_sync_service.adapter.in.web.dto;

public class CreateOrderRequest {

    private String marketplace;

    public CreateOrderRequest() {}

    public CreateOrderRequest(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getMarketplace() {
        return marketplace;
    }
}
