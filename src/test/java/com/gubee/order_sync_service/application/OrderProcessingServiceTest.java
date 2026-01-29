package com.gubee.order_sync_service.application;

import com.gubee.order_sync_service.domain.model.Order;
import com.gubee.order_sync_service.domain.model.OrderId;
import com.gubee.order_sync_service.domain.model.OrderStatus;
import com.gubee.order_sync_service.domain.port.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderProcessingServiceTest {
    @Test
    void shouldCreateAndStartProcessingOrder(){
        OrderRepository repository = mock(OrderRepository.class);
        OrderProcessingService service = new OrderProcessingService(repository);

        Order order = service.createAndStartProcessing("MERCADO_LIVRE");

        assertNotNull(order.getId());
        assertEquals(OrderStatus.PROCESSING, order.getStatus());

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(repository).save(captor.capture());

        Order savedOrder = captor.getValue();

        assertNotNull(savedOrder.getId());
        assertEquals(OrderStatus.PROCESSING, savedOrder.getStatus());
    }

    
}
