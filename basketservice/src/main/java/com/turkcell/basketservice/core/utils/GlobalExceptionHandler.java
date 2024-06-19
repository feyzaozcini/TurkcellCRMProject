package com.turkcell.basketservice.core.utils;

import com.turkcell.basketservice.core.utils.details.NotFoundExceptionDetails;
import com.turkcell.basketservice.core.utils.types.NotFoundException;
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
}