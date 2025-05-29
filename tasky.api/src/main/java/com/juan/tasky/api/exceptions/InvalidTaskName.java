package com.juan.tasky.api.exceptions;

public class InvalidTaskName extends RuntimeException {
    public InvalidTaskName(String message) {
        super(message);
    }
}
