package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.exception.BusinessRuleException;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel RegisterUser(UserModel userModel){
        if(repository.existsByEmail(userModel.getEmail())){
            throw new BusinessRuleException("Email já existe");
        }
       userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
        return repository.save(userModel);
    }
}
