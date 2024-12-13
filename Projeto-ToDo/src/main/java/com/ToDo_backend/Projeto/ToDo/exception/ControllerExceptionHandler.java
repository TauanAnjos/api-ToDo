package com.ToDo_backend.Projeto.ToDo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardError> businessRole(BusinessRuleException e, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), "Regra de negócio.", List.of(e.getMessage()), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
