package com.jrome.exceptions;

/**
 * Custom exception class indicating that an email already exist.
 */
public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
