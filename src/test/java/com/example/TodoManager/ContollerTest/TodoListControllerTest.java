package com.example.TodoManager.ContollerTest;

import com.example.TodoManager.Todo;
import com.example.TodoManager.Controller.TodoListController;
import com.example.TodoManager.Repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void setUp() {
        todoListController.clearTodos();
    }

    @Test
    public void should_return_empty_list_when_get_all_todos_given_none_extsited_todo() throws Exception {
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void should_return_all_todos_when_get_all_todos_given_todos() throws Exception {
        Todo todo1 = new Todo();
        todo1.setText("todo1");
        Todo todo2 = new Todo();
        todo2.setText("todo2");
        todoListRepository.save(todo1);
        todoListRepository.save(todo2);
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    public void should_return_404_when_get_given_an_invalid_id(){
    }

    @Test
    public void should_return_201_when_post_given_a_valid_todo() throws Exception {
        String todoJson = """
                {
                    "text": "Buy milk"
                }
                """;
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Buy milk"))
                .andExpect(jsonPath("$.done").value(false));
    }


    @Test
    public void should_return_400_when_post_given_an_existed_todo() throws Exception {
        String todoJson = """
                {
                    "text": "Buy milk"
                }
                """;
        Todo todo1 = new Todo();
        todo1.setText("Buy milk");
        todoListRepository.save(todo1);
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_422_when_post_given_a_todo_with_empty_text() throws Exception {
        String todoJson = """
                {
                    "text": "  ",
                    "done" : false
                }
                """;
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_422_when_post_given_a_todo_without_text() throws Exception {
        String todoJson = """
                {
                    "done" : false
                }
                """;
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_server_id_when_post_given_a_todo_with_id() throws Exception {
        String todoJson = """
                {
                    "id":1323,
                    "text": "Buy milk",
                    "done" : false
                }
                """;
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Buy milk"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void should_return_update_todo_when_put_given_a_valid_id() throws Exception {
        Todo todo=new Todo();
        todo.setText("Buy snacks");
        todo.setDone(true);
        long id=todoListRepository.save(todo).getId();
        String todoJson = """
                {
                    "id":123,
                    "text": "Buy milk",
                    "done" : false
                }
                """;
        mockMvc.perform(put("/todos/"+id).contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Buy milk"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void should_return_update_todo_when_put_given_id_and_update_todo_with_different_id() throws Exception {
        Todo todo=new Todo();
        todo.setText("Buy snacks");
        todo.setDone(true);
        long id=todoListRepository.save(todo).getId();
        String todoJson = """
                {
                    "id":456,
                    "text": "Buy milk",
                    "done" : false
                }
                """;
        mockMvc.perform(put("/todos/"+id).contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Buy milk"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    public void should_return_404_when_put_given_an_invalid_id() throws Exception {
        String todoJson = """
                {
                    "id":456,
                    "text": "Buy milk",
                    "done" : false
                }
                """;
        mockMvc.perform(put("/todos/"+9999).contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_422_when_put_given_an_empty_update_todo() throws Exception {
        Todo todo1 = new Todo();
        todo1.setText("Buy milk");
        long id=todoListRepository.save(todo1).getId();
        String todoJson = """
                {
                }
                """;
        mockMvc.perform(put("/todos/"+id).contentType(APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_204_when_delete_given_valid_id() throws Exception {
        Todo todo1 = new Todo();
        todo1.setText("Buy milk");
        long id=todoListRepository.save(todo1).getId();
        mockMvc.perform(delete("/todos/"+id).contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_404_when_delete_given_invalid_id() throws Exception {

        mockMvc.perform(delete("/todos/"+9999).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
