package com.juan.tasky.api.exceptions;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(String message) {
        super(message); // exceção customizada para Tasks que não existem
    }
}
