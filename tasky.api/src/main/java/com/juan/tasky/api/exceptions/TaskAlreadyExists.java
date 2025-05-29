package com.juan.tasky.api.exceptions;

public class TaskAlreadyExists extends RuntimeException {
    public TaskAlreadyExists(String message) {
        super(message);
    }
}
