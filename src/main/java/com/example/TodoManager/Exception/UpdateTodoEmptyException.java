package com.example.TodoManager.Exception;

public class UpdateTodoEmptyException extends RuntimeException {
    public UpdateTodoEmptyException(String message) {
        super(message);
    }
}
