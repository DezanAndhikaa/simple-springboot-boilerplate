package com.example.demo.controller.middleware;

import com.example.demo.common.exceptions.InvalidUserException;
import com.example.demo.dto.base.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new Response<>(500, "Something Wrong", ""));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<Response<String>> handleInvalidException(InvalidUserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new Response<>(400, "invalid user", ""));
    }
}
