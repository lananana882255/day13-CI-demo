package com.example.TodoManager.ContollerTest;

import com.example.TodoManager.Todo;
import com.example.TodoManager.TodoListController;
import com.example.TodoManager.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoListController todoListController;
    @Autowired
    private TodoListRepository todoListRepository;

    @BeforeEach
    public void setUp(){ todoListController.clearTodos();}

    @Test
    public void should_return_empty_list_when_get_all_todos_given_none_extsited_todo() throws Exception {
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void should_return_all_todos_when_get_all_todos_given_todos() throws Exception {
        Todo todo1=new Todo();
        todo1.setText("todo1");
        todo1.setDone(false);
        Todo todo2=new Todo();
        todo2.setText("todo2");
        todo2.setDone(false);
        todoListRepository.save(todo1);
        todoListRepository.save(todo2);
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

}
