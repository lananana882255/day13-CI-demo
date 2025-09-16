package com.example.TodoManager.Service;

import com.example.TodoManager.Exception.TodoAlreadyExistedException;
import com.example.TodoManager.Exception.TodoNotCreatedWithEmptyException;
import com.example.TodoManager.Exception.TodoNotFoundException;
import com.example.TodoManager.Exception.UpdateTodoEmptyException;
import com.example.TodoManager.Repository.TodoListRepository;
import com.example.TodoManager.Todo;
import com.example.TodoManager.TodoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (newTodo.getText() == null || newTodo.getText().trim().isEmpty()) {
            throw new TodoNotCreatedWithEmptyException("Text is empty.");
        }
        Todo targetTodo = findAll().stream().filter(todo -> todo.getText().equals(newTodo.getText())).findFirst().orElse(null);
        if (targetTodo != null) {
            throw new TodoAlreadyExistedException("Todo already existed.");
        }
        Todo todo = new Todo();
        todo.setText(newTodo.getText());
        todo.setDone(false);
        return todoListRepository.save(todo);
    }

    public Todo getTodo(long id) {
        Optional<Todo> todo = todoListRepository.findTodo(id);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException("Todo not found.");
        }
        return todo.get();
    }

    public Todo updateTodo(long id, TodoReq updateTodo) {
        Todo todo = getTodo(id);
        if (updateTodo.getId()==0 &&updateTodo.getText()==null&&!updateTodo.isDone()) {
            throw new UpdateTodoEmptyException("Update information is empty.");
        }
        todo.setText(updateTodo.getText());
        todo.setDone(updateTodo.isDone());
        return todoListRepository.update(todo);
    }

    public void deleteTodo(long id) {
        getTodo(id);
        todoListRepository.deleteTodo(id);
    }
}
