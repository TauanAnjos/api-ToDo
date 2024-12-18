package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserDTO(
        String firstName,
        String lastName,
        @NotBlank(message = "Email é obrigatório.") @Email(message = "Digite um email válido.") String email,
        @NotBlank(message = "A senha é obrigatoria") String password) {
    public UserModel toModel(){

        return new UserModel(null, this.firstName, this.lastName, this.email, this.password);
    }
}
