package com.example.TodoManager.Service;

import com.example.TodoManager.*;
import com.example.TodoManager.Exception.TodoAlreadyExistedException;
import com.example.TodoManager.Exception.TodoNotCreatedWithEmptyException;
import com.example.TodoManager.Repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Todo addTodo(TodoReq newTodo) {
        if(newTodo.getText()==null||newTodo.getText().trim()==""){
            throw new TodoNotCreatedWithEmptyException("Text is empty.");
        }
        Todo targetTodo=findAll().stream().filter(todo -> todo.getText().equals(newTodo.getText())).findFirst().orElse(null);
        if(targetTodo!=null){
            throw new TodoAlreadyExistedException("Todo already existed.");
        }
        Todo todo=new Todo();
        todo.setText(newTodo.getText());
        todo.setDone(false);
        return todoListRepository.save(todo);
    }
}
