package com.example.TodoManager;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private boolean done;

}
