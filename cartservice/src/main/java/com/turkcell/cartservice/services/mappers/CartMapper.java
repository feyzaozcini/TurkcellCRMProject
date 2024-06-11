package com.turkcell.cartservice.services.mappers;

import com.turkcell.cartservice.entities.Cart;
import com.turkcell.cartservice.entities.CartItem;
import com.turkcell.cartservice.services.dtos.requests.CartCreateRequest;
import com.turkcell.cartservice.services.dtos.requests.CartItemDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

//    @Mapping(source = "items.productId", target = "items.productId")
//    @Mapping(source = "items.quantity", target = "items.quantity")
    Cart cartFromCreateRequest(CartCreateRequest request);

    CartItem cartItemFromDetails(CartItemDetails details);
}
