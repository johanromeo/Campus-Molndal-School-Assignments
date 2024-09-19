package com.jromeo.internshipbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * GlobalExceptionHandler allows the custom-made InternshipNotFoundException to be thrown with message
     * and http status 404 - Not Found.
     */
    @ExceptionHandler(InternshipNotFoundException.class)
    public ResponseEntity<String> handleInternshipNotFoundException(InternshipNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
