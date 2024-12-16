package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(UUID id,
                      String title,
                      String description,
                      LocalDateTime createdDate,
                      LocalDateTime lastModifiedDate,
                      UserModel user) {
    public TaskModel toModel(){
        return new TaskModel(this.id ,this.title, this.description,this.createdDate,this.lastModifiedDate, this.user);
    }
}
