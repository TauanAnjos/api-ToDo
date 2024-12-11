package com.ToDo_backend.Projeto.ToDo.repositories;

import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
}
