package org.example.controllers;

import org.example.entities.TodoEntity;
import org.example.models.TodoDTO;
import org.example.models.TodoRequest;
import org.example.repositories.TodoRepository;
import org.example.transformers.TodoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoTransformer todoTransformer;

    @GetMapping("/todos")
    List<TodoDTO> findAllTodos() {
        return todoRepository.findAll().stream().map(todoTransformer::convertToTodo).collect(Collectors.toList());
    }

    @PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TodoDTO saveTodo(@RequestBody TodoRequest request) {
        TodoEntity entity = todoRepository.insert(TodoEntity.builder().title(request.getTitle()).content(request.getContent()).progress(request.getProgress()).deleted(request.getDeleted()).hidden(request
                .getHidden()
        ).build());
        return todoTransformer.convertToTodo(entity);
    }

    @PutMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TodoDTO updateTodoProgress(@RequestBody TodoRequest request) {
        Optional<TodoEntity> currentEntity = todoRepository.findById(request.get_id());
        if(currentEntity.isPresent()){
            currentEntity.get().setProgress(request.getProgress());
            TodoEntity entity = todoRepository.save(currentEntity.get());
            return todoTransformer.convertToTodo(entity);
        }
        throw new IllegalStateException("Entity not found for update");
    }


    @DeleteMapping("/todo/{id}")
    String deleteTodo(@PathVariable String id) {
        todoRepository.deleteById(id);
        return id;
    }

}
