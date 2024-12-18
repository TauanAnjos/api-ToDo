package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.exception.BusinessRuleException;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    public UserDTO authentication(String email, String password){
        UserModel userAuth = repository.findByEmail(email).orElseThrow(() -> new BusinessRuleException("Usuário não encontrado."));
        if(!new BCryptPasswordEncoder().matches(password, userAuth.getPassword())){
            throw new BusinessRuleException("Senha inválida.");
        }
        return userAuth.toDTO();
    }
}
