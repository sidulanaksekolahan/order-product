package com.example.exceptionhandler;

import com.example.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandling {

    @ExceptionHandler
    public ResponseEntity<AppResponse> handler(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        appResponse.setMessage(errors);
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppResponse> handler(Exception e) {

        AppResponse appResponse = new AppResponse();

        appResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        appResponse.setMessage(e.getMessage());
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppResponse> handler(ProductNotFoundException e) {

        AppResponse appResponse = new AppResponse();
        appResponse.setStatus(HttpStatus.NOT_FOUND.value());
        appResponse.setMessage(e.getMessage());
        appResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(appResponse, HttpStatus.NOT_FOUND);
    }
}


















