package com.turkcell.orderservice.services.abstracts;

import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse addOrder(OrderRequest request);
    List<OrderResponse> getAllOrders();
    boolean isOrderExistByCustomerId(int customerId);
    OrderResponse getOrderById(int id);
}
