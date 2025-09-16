package com.example.TodoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoListRepositoryDBImp implements TodoListRepository{
    @Autowired
    private TodoListJPARepository todoListJPARepository;
    @Override
    public void save(Todo todo) {
        todoListJPARepository.save(todo);
    }

    @Override
    public void clearTodos() {
        todoListJPARepository.deleteAll();
    }

    @Override
    public List<Todo> findAll() {
        return todoListJPARepository.findAll();
    }

}
