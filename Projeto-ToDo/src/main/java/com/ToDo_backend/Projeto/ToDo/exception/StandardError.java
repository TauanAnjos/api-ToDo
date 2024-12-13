package com.ToDo_backend.Projeto.ToDo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timestamp;
    private Integer status;
    private String error;
    private List<String> messages;
    private String path;

    public StandardError() {
    }

    public StandardError(Long timestamp, Integer status, String error, List<String> messages, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
