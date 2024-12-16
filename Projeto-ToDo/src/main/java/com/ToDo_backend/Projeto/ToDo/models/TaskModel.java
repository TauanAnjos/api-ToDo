package com.ToDo_backend.Projeto.ToDo.models;

import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID task_id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
    @ManyToOne(fetch = FetchType.LAZY) //Para carregar o usuário associado somente quando necessário.
    private UserModel user;

    public TaskModel() {
    }

    public TaskModel(UUID task_id, String title, String description, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UserModel user) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
    }

    public UUID getTask_id() {
        return task_id;
    }

    public void setTask_id(UUID task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
    public TaskDTO toDTO(){
        return new TaskDTO(this.task_id ,this.title, this.description,this.createdDate,this.lastModifiedDate, this.user);
    }
}
