package com.turkcell.cartservice.services.concretes;

import com.turkcell.cartservice.entities.Cart;
import com.turkcell.cartservice.entities.CartItem;
import com.turkcell.cartservice.repositories.CartRepository;
import com.turkcell.cartservice.services.abstracts.CartService;
import com.turkcell.cartservice.services.dtos.requests.CartAddItemRequest;
import com.turkcell.cartservice.services.dtos.requests.CartCreateRequest;
import com.turkcell.cartservice.services.dtos.requests.CartDeleteItemRequest;
import com.turkcell.cartservice.services.mappers.CartMapper;
import com.turkcell.cartservice.services.rules.CartBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartBusinessRules cartBusinessRules;
    public void createCart(CartCreateRequest request){
        cartBusinessRules.checkCartIsExistByAccountId(request.getAccountId());
        List<CartItem> items = request.getItems().stream().map((item)-> CartMapper.INSTANCE.cartItemFromDetails(item)).collect(Collectors.toList());
        Cart cart = CartMapper.INSTANCE.cartFromCreateRequest(request);
        cart.setActive(true);
        cart.setCreatedDate(LocalDateTime.now());
        cart.setItems(items);
        cart.setTotalAmount(calculateTotalAmount(items));
        cartRepository.save(cart);
    }
    public void addItemToCart(CartAddItemRequest request){
        cartBusinessRules.checkCartIsNotExistByAccountId(request.getAccountId());
        Cart cart= cartRepository.findByAccountId(request.getAccountId());
        List<CartItem> items = cart.getItems();
        items.add(CartMapper.INSTANCE.cartItemFromDetails(request.getItem()));
        cart.setUpdatedDate(LocalDateTime.now());
        cart.setTotalAmount(calculateTotalAmount(items));
        cartRepository.save(cart);
    }

    public void deleteItemFromCart(CartDeleteItemRequest request){
        cartBusinessRules.checkCartIsNotExistByAccountId(request.getAccountId());
        Cart cart = cartRepository.findByAccountId(request.getAccountId());
        List<CartItem> items = cart.getItems();
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
        cart.setItems(items);
        cart.setUpdatedDate(LocalDateTime.now());
        cart.setTotalAmount(calculateTotalAmount(items));
        cartRepository.save(cart);
    }
    public void clearCartById(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setTotalAmount(0);
        cart.setItems(new ArrayList<>());
        cart.setUpdatedDate(LocalDateTime.now());
        cartRepository.save(cart);
    }

    public void deleteCartById(int cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setActive(false);
        cart.setDeletedDate(LocalDateTime.now());
        cartRepository.save(cart);
    }

    public double calculateTotalAmount(List<CartItem> items){
        double totalAmount = 0;
        for(CartItem item : items){
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return totalAmount;
    }
}
