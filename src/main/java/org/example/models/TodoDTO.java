package org.example.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TodoDTO {
    String _id;
    String title;
    String content;
    LocalDate created;
    Boolean hidden;
    Boolean deleted;
    Integer progress;
}
