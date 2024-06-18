package com.turkcell.basketservice.services.mappers;

import com.turkcell.basketservice.entitites.Basket;
import com.turkcell.basketservice.entitites.BasketItem;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketItemDetails;
import com.turkcell.basketservice.services.dtos.responses.BasketGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);
    //    @Mapping(source = "items.productId", target = "items.productId")
//    @Mapping(source = "items.quantity", target = "items.quantity")
    Basket basketFromCreateRequest(BasketCreateRequest request);

    BasketItem basketItemFromDetails(BasketItemDetails details);

    BasketGetResponse getResponseFromBasket(Basket basket);


    BasketItemDetails detailsFromBasketItem(BasketItem basketItem);
}
