package com.example.ProductService.command.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex, WebRequest webRequest) {

        return ResponseHandler.generateResponse(ex.getTimestamp(), ex.getMessage(), ex.getStatus());
    }
}
