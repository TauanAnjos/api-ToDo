package com.ToDo_backend.Projeto.ToDo.repositories;

import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
