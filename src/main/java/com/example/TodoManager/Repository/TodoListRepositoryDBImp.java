package com.example.TodoManager.Repository;

import com.example.TodoManager.Entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoListRepositoryDBImp implements TodoListRepository {
    @Autowired
    private TodoListJPARepository todoListJPARepository;

    @Override
    public Todo save(Todo todo) {
        todo.setDone(false);
        return todoListJPARepository.save(todo);
    }

    @Override
    public void clearTodos() {
        todoListJPARepository.deleteAll();
    }

    @Override
    public List<Todo> findAll() {
        return todoListJPARepository.findAll();
    }

    @Override
    public Optional<Todo> findTodo(long id) {
        return todoListJPARepository.findById(id);
    }

    @Override
    public Todo update(Todo todo) {
        return todoListJPARepository.save(todo);
    }

    @Override
    public void deleteTodo(long id) {
        todoListJPARepository.deleteById(id);
    }

}
