package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;

public record TaskDTOResponse(String title,
                              String description,

                              UserDTOResponse user) {
    public TaskModel toModel(UserModel user){
        return new TaskModel(this.title, this.description, user);
    }
}
