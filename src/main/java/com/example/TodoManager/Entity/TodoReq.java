package com.example.TodoManager.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoReq {
    private String text;
    private boolean done;
}
