package com.turkcell.customerservice.core.utils;

import com.turkcell.customerservice.core.utils.details.BusinessProblemDetails;
import com.turkcell.customerservice.core.utils.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {

        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }
}
