package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTORequest;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTOResponse;
import com.ToDo_backend.Projeto.ToDo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController{

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Criar tarefa",
            description = "Endpoint de criação de tarefa.",
            tags = "task"
    )
    @PostMapping
    public ResponseEntity<TaskDTOResponse> createdTask(HttpServletRequest request,  @RequestBody TaskDTORequest taskDTORequest){
        TaskDTOResponse createdTask = taskService.createTask(getUserModelSession(request).getUser_id(), taskDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    @Operation(
            summary = "Buscar todas as tarefas",
            description = "Endpoint para buscar todas as tarefas no banco",
            tags = "task"
    )
    @GetMapping
    public ResponseEntity<List<TaskDTOResponse>> getAllTasks(HttpServletRequest request){
        UUID uuid = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(uuid));
    }
}
