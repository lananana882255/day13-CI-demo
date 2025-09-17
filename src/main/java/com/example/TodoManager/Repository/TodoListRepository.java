package com.example.TodoManager.Repository;

import com.example.TodoManager.Entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepository {
    Todo save(Todo todo);

    void clearTodos();

    List<Todo> findAll();

    Optional<Todo> findTodo(long id);

    Todo update(Todo todo);

    void deleteTodo(long id);
}
