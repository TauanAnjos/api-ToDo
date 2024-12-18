package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTO;
import com.ToDo_backend.Projeto.ToDo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Criar task",
            description = "Endpoint de criação de tarefa.",
            tags = "task"
    )
    @PostMapping("/{id}")
    public ResponseEntity<TaskDTO> createdTask(@PathVariable("id")UUID userId, @RequestBody TaskModel taskModel){
        TaskDTO createdTask = taskService.createTask(userId, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}
