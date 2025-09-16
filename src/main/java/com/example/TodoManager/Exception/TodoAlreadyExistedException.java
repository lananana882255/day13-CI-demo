package com.example.TodoManager.Exception;

public class TodoAlreadyExistedException extends RuntimeException {

    public TodoAlreadyExistedException(String meassage) {
        super(meassage);
    }
}
