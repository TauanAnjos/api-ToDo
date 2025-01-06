package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.exception.BusinessRuleException;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.UserDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public UserModel RegisterUser(UserModel userModel){
        if(repository.existsByEmail(userModel.getEmail())){
            throw new BusinessRuleException("Email já existe");
        }
       userModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
        return repository.save(userModel);
    }

    public UserDTOResponse finByUserId(UUID userId){
        UserModel userExists = repository.findById(userId).orElseThrow(()-> new BusinessRuleException("Usuário não encontrado"));
        if(!userExists.getUser_id().equals(userId)){
            throw new BusinessRuleException("Esse usuário não está logado.");
        }
        return userExists.toDTOResponse();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
