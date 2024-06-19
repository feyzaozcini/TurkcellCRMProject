package com.turkcell.basketservice.services.concretes;

import com.turkcell.basketservice.clients.CatalogServiceClient;
import com.turkcell.basketservice.entitites.Basket;
import com.turkcell.basketservice.entitites.BasketItem;
import com.turkcell.basketservice.repositories.BasketRepository;
import com.turkcell.basketservice.services.abstracts.BasketService;
import com.turkcell.basketservice.services.dtos.requests.BasketAddItemRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketCreateRequest;
import com.turkcell.basketservice.services.dtos.requests.BasketDeleteItemRequest;
import com.turkcell.basketservice.services.dtos.responses.BasketGetResponse;
import com.turkcell.basketservice.services.mappers.BasketMapper;
import com.turkcell.basketservice.services.rules.BasketBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketBusinessRules basketBusinessRules;
    private final BasketRepository basketRepository;
    private final CatalogServiceClient catalogServiceClient;
    @Override
    public BasketGetResponse createBasket(BasketCreateRequest request) {
        basketBusinessRules.checkBasketIsExistByAccountId(request.getAccountId());
        basketBusinessRules.isAccountExist(request.getAccountId());
        List<BasketItem> items = request.getItems().stream().map((item)-> BasketMapper.INSTANCE.basketItemFromDetails(item)).collect(Collectors.toList());
        for(BasketItem item : items){
            basketBusinessRules.isProductExist(item.getProductId());
        }
        Basket basket = BasketMapper.INSTANCE.basketFromCreateRequest(request);
        basket.setActive(true);
        basket.setCreatedDate(LocalDateTime.now());
        basket.setItems(items);
        basket.setTotalAmount(calculateTotalAmount(items));
        basketRepository.save(basket);
        BasketGetResponse response = BasketMapper.INSTANCE.getResponseFromBasket(basket);
        response.setItems(basket.getItems().stream().map((detail)-> BasketMapper.INSTANCE.detailsFromBasketItem(detail)).collect(Collectors.toList()));
        return response;
    }

    public BasketGetResponse getBasketByAccountId(int accountId){
        basketBusinessRules.checkBasketIsExistByAccountId(accountId);
        return BasketMapper.INSTANCE.getResponseFromBasket(basketRepository.findByAccountIdAndActive(accountId, true));
    }

    public BasketGetResponse getBasketById(int basketId){
        basketBusinessRules.checkBasketIsExistById(basketId);
        return BasketMapper.INSTANCE.getResponseFromBasket(basketRepository.findByIdAndActive(basketId,true));
    }
    @Override
    public void addItemToBasket(BasketAddItemRequest request) {
        basketBusinessRules.checkBasketIsNotExistByAccountId(request.getAccountId());
        basketBusinessRules.isAccountExist(request.getAccountId());
        basketBusinessRules.isProductExist(request.getItem().getProductId());
        Basket basket= basketRepository.findByAccountIdAndActive(request.getAccountId(), true);
        List<BasketItem> items = basket.getItems();
        items.add(BasketMapper.INSTANCE.basketItemFromDetails(request.getItem()));
        basket.setUpdatedDate(LocalDateTime.now());
        basket.setTotalAmount(calculateTotalAmount(items));
        basketRepository.save(basket);
    }

    @Override
    public void deleteItemFromBasket(BasketDeleteItemRequest request) {
        basketBusinessRules.checkBasketIsNotExistByAccountId(request.getAccountId());
        basketBusinessRules.isAccountExist(request.getAccountId());
        basketBusinessRules.isProductExist(request.getItem().getProductId());
        Basket basket = basketRepository.findByAccountIdAndActive(request.getAccountId(), true);
        List<BasketItem> items = basket.getItems();
        items.stream()
                .filter(item -> item.getProductId() == request.getItem().getProductId())
                .findFirst()
                .ifPresent(
                        item -> {
                            if (item.getQuantity() < request.getItem().getQuantity()) {
                                item.setQuantity(item.getQuantity() - request.getItem().getQuantity());
                            } else {
                                items.remove(item);
                            }
                        }
                );
        basket.setItems(items);
        basket.setUpdatedDate(LocalDateTime.now());
        basket.setTotalAmount(calculateTotalAmount(items));
        basketRepository.save(basket);
    }

    @Override
    public void clearBasketById(int basketId) {
        basketBusinessRules.checkBasketIsExistById(basketId);
        Basket basket = basketRepository.findById(basketId).orElseThrow();
        basket.setTotalAmount(0);
        basket.setItems(new ArrayList<>());
        basket.setUpdatedDate(LocalDateTime.now());
        basketRepository.save(basket);
    }

    @Override
    public void deleteBasketById(int basketId) {
        basketBusinessRules.checkBasketIsExistById(basketId);
        Basket basket = basketRepository.findById(basketId).orElseThrow();
        basket.setActive(false);
        basket.setDeletedDate(LocalDateTime.now());
        basketRepository.save(basket);
    }
    public double calculateTotalAmount(List<BasketItem> items){
        double totalAmount = 0;
        for(BasketItem item : items){
            totalAmount += item.getQuantity() * catalogServiceClient.getProductById(item.getProductId()).getPrice();
        }
        return totalAmount;
    }
}
