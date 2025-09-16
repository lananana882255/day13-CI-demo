package com.example.TodoManager.Repository;

import com.example.TodoManager.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository {
    Todo save(Todo todo);

    void clearTodos();

    List<Todo> findAll();

    Todo findTodo(long id);
}
