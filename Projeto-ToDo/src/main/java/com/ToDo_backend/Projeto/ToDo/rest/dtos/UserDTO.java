package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;

public record UserDTO(
        @NotBlank(message = "Email é obrigatorio.") @Email(message = "Digite um email válido.") String email,
        @NotBlank(message = "A senha é obrigatoria") String password) {
    public UserModel toModel(){

        return new UserModel(null, this.email,this.password);
    }
}
