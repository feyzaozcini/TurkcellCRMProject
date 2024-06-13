package com.turkcell.orderservice.services.rules;

import com.turkcell.orderservice.clients.CatalogServiceClient;
import com.turkcell.orderservice.clients.CustomerServiceClient;
import com.turkcell.orderservice.core.utils.types.NotFoundException;
import com.turkcell.orderservice.repositories.OrderRepository;
import com.turkcell.orderservice.clients.dtos.productservice.ProductGetResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBusinessRules {
    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    public void isDefaultAddressExist(int customerId){
        if(customerServiceClient.getCustomer(customerId).getDefaultAddressId() == 0)
            throw new NotFoundException("Default address not found for customer ID: " + customerId);
    }

    public void isProductExist(int productId){
        try{
            ProductGetResponse response = catalogServiceClient.getProductById(productId);
        }
        catch (FeignException.NotFound exception){
            throw new NotFoundException("Product Not Found!");
        }
    }
    public void isOrderExist(int id){
        if(!orderRepository.existsById(id) || !orderRepository.findById(id).orElseThrow().isActive())
            throw new NotFoundException("Order Not Found!");
    }
}
