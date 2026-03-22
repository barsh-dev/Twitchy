package com.barshdev.twitchy.exceptions;

public class InvalidArgException extends RuntimeException {
    public InvalidArgException(String message) {
        super(message);
    }
}
