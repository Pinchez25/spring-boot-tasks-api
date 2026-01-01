package com.peter.mappers.impl;

import com.peter.domain.dto.TaskPatchRequest;
import com.peter.domain.dto.TaskRequest;
import com.peter.domain.dto.TaskResponse;
import com.peter.domain.entities.Task;
import com.peter.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .dueDate(request.dueDate())
                .status(request.status())
                .priority(request.priority())
                .build();
    }

    @Override
    public TaskResponse toResponse(Task entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDueDate(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getTaskList().getId(),
                entity.getCreated(),
                entity.getUpdated()
        );
    }

    @Override
    public void updateEntity(Task entity, TaskPatchRequest request) {
        if (request.title() != null) {
            entity.setTitle(request.title());
        }
        if (request.description() != null) {
            entity.setDescription(request.description());
        }
        if (request.dueDate() != null) {
            entity.setDueDate(request.dueDate());
        }
        if (request.status() != null) {
            entity.setStatus(request.status());
        }
        if (request.priority() != null) {
            entity.setPriority(request.priority());
        }
    }
}
