package com.turkcell.orderservice.core.utils.types;

import feign.FeignException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
