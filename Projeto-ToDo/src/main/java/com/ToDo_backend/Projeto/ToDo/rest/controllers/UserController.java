package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTORequest;
import com.ToDo_backend.Projeto.ToDo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
