package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void RegisterUser(String email, String password){
        if(repository.existsByEmail(email)){
            throw new IllegalArgumentException("Email já existe");
        }
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        repository.save(user);
    }
}
