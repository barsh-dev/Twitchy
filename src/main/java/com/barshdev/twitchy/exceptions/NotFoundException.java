package com.barshdev.twitchy.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg + " not found");
    }
}
