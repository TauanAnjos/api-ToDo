package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTORequest;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTOResponse;
import com.ToDo_backend.Projeto.ToDo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<TaskDTOResponse>> getAllTasks(HttpServletRequest request, @Parameter(description = "Número da página começa em '1' ") @RequestParam(defaultValue = "1")int page,@Parameter(description = "Quantidade de item por página") @RequestParam(defaultValue = "10")int size){
        UUID uuid = getUserModelSession(request).getUser_id();
        // Ajusta o número da página para que a 1 seja considerada a página 0
        Pageable pageable = PageRequest.of(page > 0 ? page - 1: 0, size);
        Page<TaskDTOResponse> taskPage = taskService.getAllTasks(uuid, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(taskPage);
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
    public ResponseEntity<TaskDTOResponse> updateTask(@PathVariable("taskId") UUID idTask,HttpServletRequest request, @RequestBody TaskDTORequest taskDTORequest){
        UUID idUser = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(idUser, idTask, taskDTORequest));
    }
    @Operation(
            summary = "Excluir tarefa",
            description = "Endpoint para excluir uma tarefa específica associada ao usuário autenticado. Permite a remoção definitiva da tarefa com base em seu identificador.",
            tags = {"Tarefas"}
    )
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId")UUID taskId, HttpServletRequest request){
        UUID userId = getUserModelSession(request).getUser_id();
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluida com sucesso.");
    }
}
