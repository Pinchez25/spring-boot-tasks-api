package com.peter.domain.dto;

import com.peter.domain.entities.TaskPriority;
import com.peter.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
       UUID id,
       String title,
       String description,
       LocalDateTime dueDate,
       TaskStatus status,
       TaskPriority priority,
       UUID taskListId,
       LocalDateTime created,
       LocalDateTime updated
) {
}
