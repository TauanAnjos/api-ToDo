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
            summary = "Criar uma nova tarefa",
            description = "Endpoint para criar uma nova tarefa associada ao usuário autenticado.",
            tags = {"Tarefas"}
    )
    @PostMapping
    public ResponseEntity<TaskDTOResponse> createdTask(HttpServletRequest request,  @RequestBody TaskDTORequest taskDTORequest){
        TaskDTOResponse createdTask = taskService.createTask(getUserModelSession(request).getUser_id(), taskDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
    @Operation(
            summary = "Listar todas as tarefas do usuário",
            description = "Endpoint para consultar todas as tarefas cadastradas no sistema, associadas ao usuário autenticado.",
            tags = {"Tarefas"}
    )
    @GetMapping
    public ResponseEntity<List<TaskDTOResponse>> getAllTasks(HttpServletRequest request){
        UUID uuid = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(uuid));
    }
    @Operation(
            summary = "Consultar tarefa por ID",
            description = "Endpoint para buscar uma tarefa específica no sistema pelo seu identificador único (ID). Retorna os detalhes da tarefa somente se ela pertencer ao usuário autenticado.",
            tags = {"Tarefas"}
    )
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTOResponse> findByTaskById(@PathVariable("taskId") UUID idTask, HttpServletRequest request){
        UUID idUser = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(taskService.taskFindById(idTask, idUser));
    }
    @Operation(
            summary = "Atualizar tarefa",
            description = "Endpoint para atualizar os dados de uma tarefa específica associada ao usuário autenticado. Permite alterar o título e a descrição da tarefa, desde que preenchidos corretamente.",
            tags = {"Tarefas"}
    )

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTOResponse> taskUpdade(@PathVariable("taskId") UUID idTask,HttpServletRequest request, @RequestBody TaskDTORequest taskDTORequest){
        UUID idUser = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(idUser, idTask, taskDTORequest));
    }
}
