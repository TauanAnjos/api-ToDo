package com.ToDo_backend.Projeto.ToDo.rest.dtos;

import java.util.UUID;

public record TaskDTORequest(String title,
                             String description,
                             UUID userId) {
}
