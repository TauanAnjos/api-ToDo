package com.ToDo_backend.Projeto.ToDo.rest.controllers;



// ... outros imports (omitidos) ...

import com.ToDo_backend.Projeto.ToDo.rest.dtos.AuthDTO;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TokenDTO;
import com.ToDo_backend.Projeto.ToDo.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            summary = "Autenticar usuário",
            description = "Endpoint para autenticar um usuário no sistema. Requer o envio de e-mail e senha válidos, retornando um token JWT em caso de sucesso.",
            tags = {"Autenticação"}
    )

    @PostMapping("/login")
    public TokenDTO login(@RequestBody AuthDTO authDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken(authDto.email());

        return new TokenDTO(token);
    }
}