package com.ToDo_backend.Projeto.ToDo.repositories;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUser_UserId(UUID userId);
}
