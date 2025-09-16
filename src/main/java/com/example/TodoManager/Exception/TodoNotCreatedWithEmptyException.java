package com.example.TodoManager.Exception;

public class TodoNotCreatedWithEmptyException extends RuntimeException {
    public TodoNotCreatedWithEmptyException(String message) {
        super(message);
    }
}
