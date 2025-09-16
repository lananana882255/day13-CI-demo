package com.example.TodoManager;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository {
    void save(Todo todo);

    void clearTodos();

    List<Todo> findAll();
}
