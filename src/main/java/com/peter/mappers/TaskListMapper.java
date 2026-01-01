package com.peter.mappers;


import com.peter.domain.dto.TaskListRequest;
import com.peter.domain.dto.TaskListResponse;
import com.peter.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList toEntity(TaskListRequest request);
    TaskListResponse toResponse(TaskList entity);
    void updateEntity(TaskList entity, TaskListRequest request);
}
