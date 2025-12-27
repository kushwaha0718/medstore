package com.nitish.medstore.exceptions;

public class NoProductFoundException extends RuntimeException {
    public NoProductFoundException(String message) {
        super(message);
    }
}
