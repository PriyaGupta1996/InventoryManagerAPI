package com.craftdemo.inventorymanager.exception;

// Class to handle exception Globally

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse
    handleResourceNotFoundException(Exception ex) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {BadRequestException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleBadRequest(Exception ex)
    {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
