package com.turkcell.customerservice.core.utils.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
