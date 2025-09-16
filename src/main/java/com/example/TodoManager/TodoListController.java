package com.example.TodoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/todos")
@RestController
public class TodoListController {
    @Autowired
    private TodoListService todoListService;
    public void clearTodos() {
        todoListService.clearTodos();
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoListService.findAll());
    }
}
