package com.clinica.handler;
//manipulador de excessões

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.clinica.handler.exception.ResourceNotFoundException;
import com.clinica.handler.error.ErrorMessage;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorMessage error=new ErrorMessage("Not found",HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
