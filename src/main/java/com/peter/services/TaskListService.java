package com.peter.services;

import com.peter.domain.dto.TaskListRequest;
import com.peter.domain.dto.TaskListResponse;
import com.peter.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
   List<TaskListResponse> getAllTaskLists();
   TaskListResponse findById(UUID id);
   TaskListResponse create(TaskListRequest request);
   TaskListResponse update(UUID id, TaskListRequest request);
   void delete(UUID id);
}
