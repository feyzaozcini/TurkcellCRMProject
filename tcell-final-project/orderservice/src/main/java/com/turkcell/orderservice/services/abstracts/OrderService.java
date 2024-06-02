package com.turkcell.orderservice.services.abstracts;

import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;

public interface OrderService {
    OrderResponse addOrder(OrderRequest request);
    String getToken();
}
