package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.exception.BusinessRuleException;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTORequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authentication(String email, String password){

       try{
           UsernamePasswordAuthenticationToken usernamePassword =
                   new UsernamePasswordAuthenticationToken(email, password);
           authenticationManager.authenticate(usernamePassword);

           return tokenService.generateToken(email);

       }catch (BadCredentialsException e){
           throw new BusinessRuleException("E-mail ou senha inválida." , e);
       }catch (Exception e){
           throw new BusinessRuleException("Erro ao tentar autenticar usuário.", e);
       }

    }
}
