package com.example.TodoManager.ServiceTest;

import com.example.TodoManager.Exception.TodoAlreadyExistedException;
import com.example.TodoManager.Exception.TodoNotCreatedWithEmptyException;
import com.example.TodoManager.Exception.TodoNotFoundException;
import com.example.TodoManager.Repository.TodoListRepository;
import com.example.TodoManager.Service.TodoListService;
import com.example.TodoManager.Exception.UpdateTodoEmptyException;
import com.example.TodoManager.Todo;
import com.example.TodoManager.TodoReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TodoListServiceTest {
    @InjectMocks
    private TodoListService todoListService;
    @Mock
    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUp() {
        todoListService.clearTodos();
    }

    @Test
    public void should_return_TodoAlreadyExistedException_when_add_given_an_existed_todo() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setText("Buy milk");
        todo.setDone(false);
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo);
        when(todoListRepository.findAll()).thenReturn(todoList);
        TodoReq todo1 = new TodoReq();
        todo1.setId(1);
        todo1.setText("Buy milk");
        todo1.setDone(false);
        assertThrows(TodoAlreadyExistedException.class, () -> todoListService.addTodo(todo1));
        verify(todoListRepository, times(0)).save(any());
    }

    @Test
    public void should_return_TodoNotCreatedWithEmptyTextException_when_post_given_a_todo_with_empty_text() {
        TodoReq todo = new TodoReq();
        todo.setId(1);
        todo.setText("   ");
        todo.setDone(false);
        assertThrows(TodoNotCreatedWithEmptyException.class, () -> todoListService.addTodo(todo));
        verify(todoListRepository, times(0)).save(any());
    }

    @Test
    public void should_return_TodoNotCreatedWithEmptyTextException_when_post_given_a_todo_without_text() {
        TodoReq todo = new TodoReq();
        todo.setId(1);
        todo.setDone(false);
        assertThrows(TodoNotCreatedWithEmptyException.class, () -> todoListService.addTodo(todo));
        verify(todoListRepository, times(0)).save(any());
    }

    @Test
    public void should_return_TodoNotFoundException_when_put_given_an_invalid_id() throws Exception {
        TodoReq todo = new TodoReq();
        todo.setId(999);
        todo.setDone(false);
        todo.setText("buy milk");
        assertThrows(TodoNotFoundException.class, () -> todoListService.updateTodo(999,todo));
        verify(todoListRepository, times(0)).update(any());
    }

    @Test
    public void should_return_EmptyUpdateTodoException_when_put_given_an_empty_update_todo() throws Exception {
        Todo todo= new Todo();
        todo.setId(1);
        todo.setText("buy milk");
        todo.setDone(false);
        TodoReq updateTodo=new TodoReq();
        todoListRepository.save(todo);
        when(todoListRepository.findTodo(1)).thenReturn(Optional.of(todo));
        assertThrows(UpdateTodoEmptyException.class, () -> todoListService.updateTodo(1,updateTodo));
        verify(todoListRepository, times(0)).update(any());
    }
}
