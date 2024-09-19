package com.jrome.exceptions;

/**
 * Custom exception class indicating that a username already exist.
 */
public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
