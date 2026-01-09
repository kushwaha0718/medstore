package com.nitish.medstore.exceptions;

public class NoAdminFound extends RuntimeException {
    public NoAdminFound(String message) {
        super(message);
    }
}
