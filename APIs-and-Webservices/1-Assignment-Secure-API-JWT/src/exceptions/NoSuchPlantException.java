package com.jrome.exceptions;

/**
 * Custom exception class indicating that a plant was not found.
 */
public class NoSuchPlantException extends RuntimeException {

    public NoSuchPlantException(String message) {
        super(message);
    }
}
