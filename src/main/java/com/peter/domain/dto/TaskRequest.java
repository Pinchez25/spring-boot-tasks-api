package com.peter.domain.dto;

import com.peter.domain.entities.TaskPriority;
import com.peter.domain.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequest(
       @NotBlank(message = "Title is required")
       String title,
       String description,
       LocalDateTime dueDate,
//       @NotNull(message = "Status is required")
       TaskStatus status,
//       @NotNull(message = "Priority is required")
       TaskPriority priority,
       @NotNull(message = "Task list ID is required")
       UUID taskListId
) {
}
