package com.example.TodoManager.Controller;

import com.example.TodoManager.Todo;
import com.example.TodoManager.Service.TodoListService;
import com.example.TodoManager.TodoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody TodoReq todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoListService.addTodo(todo));
    }
}
