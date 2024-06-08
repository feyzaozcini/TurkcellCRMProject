package com.turkcell.orderservice.services.concretes;
import com.turkcell.orderservice.clients.CatalogServiceClient;
import com.turkcell.orderservice.clients.CustomerServiceClient;
import com.turkcell.orderservice.core.utils.types.NotFoundException;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import com.turkcell.orderservice.services.dtos.responses.ProductGetResponse;
import com.turkcell.orderservice.services.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    private String token;
    public OrderResponse addOrder(OrderRequest request){
        this.token = request.getToken();
        Order order = OrderMapper.INSTANCE.orderFromRequest(request);
        order.setServiceAddress(getServiceAddressId(request.getCustomerId()));
        order.setTotalAmount(getTotalAmount(request));
        orderRepository.save(order);
        return buildResponse(order);
    }

    public int getServiceAddressId(int customerId){
        if(customerServiceClient.getCustomer(customerId).getDefaultAddressId() == 0)
            throw new NotFoundException("Default address not found");
        return customerServiceClient.getCustomer(customerId).getDefaultAddressId();
    }

    public OrderResponse buildResponse(Order order){
        OrderResponse response = OrderMapper.INSTANCE.responseFromOrder(order);
        response.setServiceAddress(customerServiceClient.getAddressDetails(order.getServiceAddress()));
        response.setProducts(getProductsById(order.getProductIds()));
        return response;
    }

    public float getTotalAmount(OrderRequest request){
        try{
            float totalAmount = 0;
            for(int productId : request.getProductIds()){
                totalAmount+= catalogServiceClient.getProductById(productId).getPrice();
            }
            return totalAmount;
        }
        catch (NotFoundException exception){
            throw new NotFoundException("Product Not Found!");
        }
    }

    public List<ProductGetResponse> getProductsById(Set<Integer>ids){
        Set<ProductGetResponse> products = new HashSet<>();
        for(int productId : ids){
            products.add(catalogServiceClient.getProductById(productId));
        }
        return products.stream().toList();
    }

    public List<OrderResponse> getAllOrders(String token){
        this.token =token;
        List<OrderResponse> responses = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            responses.add(buildResponse(order));
            orderRepository.save(order);
        }
        return responses;
    }

    public String getToken(){
        return this.token;
    }
}
