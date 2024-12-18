package com.ToDo_backend.Projeto.ToDo.repositories;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
