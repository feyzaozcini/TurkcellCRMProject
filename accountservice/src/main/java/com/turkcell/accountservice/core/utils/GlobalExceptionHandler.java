package com.turkcell.accountservice.core.utils;

import com.turkcell.accountservice.core.utils.details.BusinessExceptionDetails;
import com.turkcell.accountservice.core.utils.details.NotFoundExceptionDetails;
import com.turkcell.accountservice.core.utils.types.BusinessException;
import com.turkcell.accountservice.core.utils.types.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundExceptionDetails handleNotFoundException(NotFoundException exception){
        NotFoundExceptionDetails notFoundDetails = new NotFoundExceptionDetails();
        notFoundDetails.setMessage(exception.getMessage());
        return notFoundDetails;
    }
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleBusinessException(BusinessException exception){
        BusinessExceptionDetails details = new BusinessExceptionDetails();
        details.setMessage(exception.getMessage());
        return details;
    }
}
