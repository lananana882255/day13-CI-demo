package com.example.TodoManager.Service;

public class UpdateTodoEmptyException extends RuntimeException {
    public UpdateTodoEmptyException(String message) {
        super(message);
    }
}
