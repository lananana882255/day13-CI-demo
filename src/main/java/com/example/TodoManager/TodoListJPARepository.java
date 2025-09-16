package com.example.TodoManager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListJPARepository extends JpaRepository<Todo, Long> {

}
