package com.turkcell.orderservice.services.concretes;

import com.turkcell.orderservice.clients.CatalogServiceClient;
import com.turkcell.orderservice.clients.CustomerServiceClient;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import com.turkcell.orderservice.services.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private String token;
    public OrderResponse addOrder(OrderRequest request){
        this.token = request.getToken();
        Order order = OrderMapper.INSTANCE.orderFromRequest(request);
        System.out.println(customerServiceClient.getDefaultCustomerAddress(request.getCustomerId()));
        order.setServiceAddress(customerServiceClient.getDefaultCustomerAddress(request.getCustomerId()));
        float totalAmount = 0;
        for(int productId : request.getProductIds()){
            totalAmount+= catalogServiceClient.getPriceById(productId);
        }
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        OrderResponse response = OrderMapper.INSTANCE.responseFromOrder(order);
        response.setServiceAddress(customerServiceClient.getAddressDetails(order.getServiceAddress()));
        return response;
    }

    public String getToken(){
        return this.token;
    }
}
