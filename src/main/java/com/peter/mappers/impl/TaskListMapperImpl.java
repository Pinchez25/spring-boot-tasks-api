package com.peter.mappers.impl;

import com.peter.domain.dto.TaskListRequest;
import com.peter.domain.dto.TaskListResponse;
import com.peter.domain.entities.TaskList;
import com.peter.mappers.TaskListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {


    @Override
    public TaskList toEntity(TaskListRequest request) {
        return TaskList.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    @Override
    public TaskListResponse toResponse(TaskList entity) {
        return new TaskListResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCreated(),
                entity.getUpdated()
        );
    }

    @Override
    public void updateEntity(TaskList entity, TaskListRequest request) {
        entity.setTitle(request.title());
        entity.setDescription(request.description());
    }
}
