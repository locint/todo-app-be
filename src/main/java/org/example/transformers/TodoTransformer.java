package org.example.transformers;

import org.example.entities.TodoEntity;
import org.example.models.TodoDTO;
import org.springframework.stereotype.Component;

@Component
public class TodoTransformer {

    public TodoDTO convertToTodo(TodoEntity entity){
        return TodoDTO.builder()._id(entity.get_id()).title(entity.getTitle()).progress(entity.getProgress()).deleted(entity.getDeleted()).created(entity
                .getCreated()).content(entity.getContent()).build();
    }
}
