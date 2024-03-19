package org.example.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Data
@Document("todos")
public class TodoEntity {
    @Id
    String _id;
    String title;
    String content;
    LocalDate created;
    Boolean hidden;
    Boolean deleted;
    Integer progress;
}
