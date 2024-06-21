package com.turkcell.orderservice.services.concretes;
import com.turkcell.common.events.InvoiceEvent;
import com.turkcell.orderservice.clients.CatalogServiceClient;
import com.turkcell.orderservice.clients.CustomerServiceClient;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderProductResponse;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import com.turkcell.orderservice.services.mappers.OrderMapper;
import com.turkcell.orderservice.services.rules.OrderBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final OrderBusinessRules orderBusinessRules;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public OrderResponse addOrder(OrderRequest request){
        Order order = OrderMapper.INSTANCE.orderFromRequest(request);
        order.setServiceAddress(getServiceAddressId(request.getCustomerId()));
        order.setTotalAmount(getTotalAmount(request));
        order.setCreatedDate(LocalDateTime.now());
        order = orderRepository.save(order);
        InvoiceEvent invoiceEvent = new InvoiceEvent(order.getCustomerId(), order.getAccountId(), order.getServiceAddress(), new ArrayList<>(), order.getTotalAmount());
        kafkaTemplate.sendDefault("NewOrder", invoiceEvent);
        return buildResponse(order);
    }

    public int getServiceAddressId(int customerId) {
        orderBusinessRules.isDefaultAddressExist(customerId);
        return customerServiceClient.getCustomer(customerId).getDefaultAddressId();
    }

    public OrderResponse buildResponse(Order order){
        OrderResponse response = OrderMapper.INSTANCE.responseFromOrder(order);
        response.setServiceAddress(customerServiceClient.getAddressDetails(order.getServiceAddress()));
        response.setProducts(getProductResponsesByIds(order.getProductIds()));
        return response;
    }

    public float getTotalAmount(OrderRequest request) {
        float totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : request.getProductIds().entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            orderBusinessRules.isProductExist(productId);
            float productPrice = catalogServiceClient.getProductById(productId).getPrice();
            totalAmount += productPrice * quantity;
        }
        return totalAmount;
    }

    public List<OrderProductResponse> getProductResponsesByIds(Map<Integer, Integer> products){
        List<OrderProductResponse> responses = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            OrderProductResponse response = OrderMapper.INSTANCE.productResponseFromProduct(catalogServiceClient.getProductById(productId));
            response.setQuantity(quantity);
            responses.add(response);
        }
        return responses;
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
