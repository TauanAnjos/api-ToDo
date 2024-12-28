package com.ToDo_backend.Projeto.ToDo.repositories;

import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    @Query("SELECT tm FROM TaskModel tm " +
            "JOIN tm.user u " +
            "WHERE u.user_id = :user_id ")
    Optional<List<TaskModel>> findAllByUserId(UUID user_id);
}
