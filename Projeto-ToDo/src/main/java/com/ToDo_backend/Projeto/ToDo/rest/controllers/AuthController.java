package com.ToDo_backend.Projeto.ToDo.rest.controllers;

import com.ToDo_backend.Projeto.ToDo.rest.dtos.AuthDTO;
import com.ToDo_backend.Projeto.ToDo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody AuthDTO authDTO){
        service.authentication(authDTO.email(), authDTO.password());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário logado com sucesso.");
    }
}
