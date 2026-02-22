package io.github.tbarissatir.luv_todo.dto;

import io.github.tbarissatir.luv_todo.model.Task;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Task.Status status
) {}


