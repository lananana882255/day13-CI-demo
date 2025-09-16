package com.example.TodoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;
    public void clearTodos() {
        todoListRepository.clearTodos();
    }

    public List<Todo> findAll() {
        return todoListRepository.findAll();
    }
}
