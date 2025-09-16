package com.example.TodoManager.Repository;

import com.example.TodoManager.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListJPARepository extends JpaRepository<Todo, Long> {

}
