package com.ToDo_backend.Projeto.ToDo.models;

import jakarta.persistence.*;

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
    @ManyToOne(fetch = FetchType.LAZY) //Para carregar o usuário associado somente quando necessário.
    private UserModel user;

    public TaskModel() {
    }

    public TaskModel(UUID task_id, String title, String description, UserModel user) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
