package com.turkcell.orderservice.services.concretes;
import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.orderservice.clients.CatalogServiceClient;
import com.turkcell.orderservice.clients.CustomerServiceClient;
import com.turkcell.orderservice.entities.BaseEntity;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import com.turkcell.orderservice.clients.dtos.productservice.ProductGetResponse;
import com.turkcell.orderservice.services.mappers.OrderMapper;
import com.turkcell.orderservice.services.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final OrderBusinessRules orderBusinessRules;
    public OrderResponse addOrder(OrderRequest request){
        Order order = OrderMapper.INSTANCE.orderFromRequest(request);
        order.setServiceAddress(getServiceAddressId(request.getCustomerId()));
        order.setTotalAmount(getTotalAmount(request));
        order.setCreatedDate(LocalDateTime.now());
        orderRepository.save(order);
        InvoiceEvent invoiceEvent = new InvoiceEvent(order.getCustomerId(), order.getAccountId(), order.getServiceAddress(), order.getProductIds().stream().toList(), order.getTotalAmount());
        return buildResponse(order);
    }

    public int getServiceAddressId(int customerId) {
        orderBusinessRules.isDefaultAddressExist(customerId);
        return customerServiceClient.getCustomer(customerId).getDefaultAddressId();
    }

    public OrderResponse buildResponse(Order order){
        OrderResponse response = OrderMapper.INSTANCE.responseFromOrder(order);
        response.setServiceAddress(customerServiceClient.getAddressDetails(order.getServiceAddress()));
        response.setProducts(getProductsById(order.getProductIds()));
        return response;
    }

    public float getTotalAmount(OrderRequest request) {
        float totalAmount = 0;
        for (int productId : request.getProductIds()) {
            orderBusinessRules.isProductExist(productId);
            totalAmount += catalogServiceClient.getProductById(productId).getPrice();
        }
        return totalAmount;
    }

    public List<ProductGetResponse> getProductsById(Set<Integer>ids){
        Set<ProductGetResponse> products = new HashSet<>();
        for(int productId : ids){
            products.add(catalogServiceClient.getProductById(productId));
        }
        return products.stream().toList();
    }

    public List<OrderResponse> getAllOrders(){

        List<OrderResponse> responses = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            responses.add(buildResponse(order));
            orderRepository.save(order);
        }
        return responses;
    }

    public OrderResponse getOrderById(int id){
        orderBusinessRules.isOrderExist(id);
        return buildResponse(orderRepository.findById(id).orElseThrow());
    }

    public boolean isOrderExistByCustomerId(int customerId){
        return orderRepository.existsByCustomerId(customerId);
    }

}
