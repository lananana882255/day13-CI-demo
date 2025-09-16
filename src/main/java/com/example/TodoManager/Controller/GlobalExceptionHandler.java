package com.example.TodoManager.Controller;

import com.example.TodoManager.Exception.TodoAlreadyExistedException;
import com.example.TodoManager.Exception.TodoNotCreatedWithEmptyException;
import com.example.TodoManager.Exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoNotCreatedWithEmptyException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleTodoNotCreatedWithEmptyException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTodoNotFoundException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(TodoAlreadyExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTodoAlreadyExistedException(Exception e) {
        return e.getMessage();
    }
}
