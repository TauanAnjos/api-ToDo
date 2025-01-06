package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTORequest;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTOResponse;
import com.ToDo_backend.Projeto.ToDo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService service;

    @Operation(
            summary = "Registrar novo usuário",
            description = "Endpoint para registrar um novo usuário no sistema. Requer o envio de dados válidos, como nome, e-mail e senha. Retorna uma mensagem de sucesso ao finalizar o registro.",
            tags = {"Usuários"}
    )
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserDTORequest userDTO){
        service.RegisterUser(userDTO.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
    @Operation(
            summary = "Buscar informações do usuário",
            description = "Endpoint para buscar os dados do usuário logado no sistema. O ID do usuário é obtido a partir da sessão e utilizado para buscar as informações no banco de dados. Retorna os dados do usuário ou lança um erro caso o usuário não seja encontrado.",
            tags = {"Usuários"}
    )
    @GetMapping
    public ResponseEntity<UserDTOResponse> findByUser(HttpServletRequest request){
        UUID userId = getUserModelSession(request).getUser_id();
        return ResponseEntity.status(HttpStatus.OK).body(service.finByUserId(userId));
    }
}
