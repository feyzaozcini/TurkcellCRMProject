package com.turkcell.orderservice.services.mappers;

import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.services.dtos.requests.OrderRequest;
import com.turkcell.orderservice.services.dtos.responses.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderFromRequest(OrderRequest request);

    @Mapping(target = "serviceAddress", ignore = true)
    @Mapping(target = "products", ignore = true)
    OrderResponse responseFromOrder(Order order);
}
