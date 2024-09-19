package com.jrome.exceptions;

import com.jrome.exceptions.payloads.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Global exception handler to handle specific exceptions across all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the NoSuchPlantException and returns a ResponseEntity with appropriate error details.
     *
     * @param e The NoSuchPlantException to be handled.
     * @return ResponseEntity containing error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NoSuchPlantException.class) ResponseEntity<ErrorDetails> throwPlantNotFoundException(
            NoSuchPlantException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the UsernameAlreadyExistException and returns a ResponseEntity with appropriate error details.
     *
     * @param e The UsernameAlreadyExistException to be handled.
     * @return ResponseEntity containing error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(UsernameAlreadyExistException.class) ResponseEntity<ErrorDetails> throwUsernameAlreadyExistException(
            UsernameAlreadyExistException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the EmailAlreadyExistException and returns a ResponseEntity with appropriate error details.
     *
     * @param e The EmailAlreadyExistException to be handled.
     * @return ResponseEntity containing error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(EmailAlreadyExistException.class) ResponseEntity<ErrorDetails> throwEmailAlreadyExistException(
            EmailAlreadyExistException e) {

        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
