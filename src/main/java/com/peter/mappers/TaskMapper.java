package com.peter.mappers;

import com.peter.domain.dto.TaskPatchRequest;
import com.peter.domain.dto.TaskRequest;
import com.peter.domain.dto.TaskResponse;
import com.peter.domain.entities.Task;

public interface TaskMapper {
    Task toEntity(TaskRequest request);
    TaskResponse toResponse(Task entity);
//    void updateEntity(Task entity, TaskRequest request);
    void updateEntity(Task entity, TaskPatchRequest request);
}
