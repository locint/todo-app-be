package org.example.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoRequest {
    String _id;
    String title;
    String content;
    Boolean hidden;
    Boolean deleted;
    Integer progress;
}
