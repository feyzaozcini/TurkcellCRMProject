package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.services.abstracts.OrderService;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/addOrder")
    public OrderResponse addOrder(@RequestBody OrderRequest request){
        return orderService.addOrder(request);
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/isExistByCustomerId/{customerId}")
    public boolean isOrderExistByCustomerId(@PathVariable int customerId){
        return orderService.isOrderExistByCustomerId(customerId);
    }
}
