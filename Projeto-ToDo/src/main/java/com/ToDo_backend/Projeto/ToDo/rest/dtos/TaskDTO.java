package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(String title,
                      String description,

                      UserModel user) {
    public TaskModel toModel(){
        return new TaskModel(this.title, this.description, this.user);
    }
}
