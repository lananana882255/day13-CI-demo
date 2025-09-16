package com.example.TodoManager.Repository;

import com.example.TodoManager.Todo;
import com.example.TodoManager.Exception.TodoNotFoundException;
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
    public Todo findTodo(long id) {
        Optional<Todo> addTodo=todoListJPARepository.findById(id);
        if(addTodo.isEmpty()){
            throw new TodoNotFoundException("Todo not found.");
        }
        return addTodo.get();
    }

}
