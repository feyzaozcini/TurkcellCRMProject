package com.turkcell.orderservice.core.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.orderservice.core.utils.details.NotFoundDetails;
import com.turkcell.orderservice.core.utils.types.NotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundDetails handleNotFoundException(NotFoundException exception) {
        NotFoundDetails notFoundDetails = new NotFoundDetails();
        notFoundDetails.setMessage(exception.getMessage());
        return notFoundDetails;
    }
//    @ExceptionHandler({FeignException.class})
//    public ResponseEntity<NotFoundDetails> handleFeignException(FeignException exception) {
//        String exceptionBody = exception.contentUTF8();
//        NotFoundDetails notFoundDetails = new NotFoundDetails();
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            notFoundDetails = objectMapper.readValue(exceptionBody, NotFoundDetails.class);
//        } catch (JsonProcessingException e) {
//            notFoundDetails.setTitle("Error Parsing Feign Exception");
//            notFoundDetails.setMessage("Error message could not be parsed.");
//        }
//
//        return new ResponseEntity<>(notFoundDetails, HttpStatus.NOT_FOUND);
//    }
}
