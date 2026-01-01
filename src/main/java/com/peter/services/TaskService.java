package com.peter.services;

import com.peter.domain.dto.TaskPatchRequest;
import com.peter.domain.dto.TaskRequest;
import com.peter.domain.dto.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponse> getAllTasks();
    TaskResponse findById(UUID id);
    List<TaskResponse> findByTaskListId(UUID taskListId);
    TaskResponse create(TaskRequest request);
    TaskResponse update(UUID id, TaskPatchRequest request);
    void delete(UUID id);
}
