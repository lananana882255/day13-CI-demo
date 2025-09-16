package com.example.TodoManager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todolist")
public class Todo {
    @Id
    private long id;
    private String text;
    private boolean done;

}
