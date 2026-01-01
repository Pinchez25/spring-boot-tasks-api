package com.peter.services.imp;

import com.peter.domain.dto.TaskPatchRequest;
import com.peter.domain.dto.TaskRequest;
import com.peter.domain.dto.TaskResponse;
import com.peter.domain.entities.Task;
import com.peter.domain.entities.TaskList;
import com.peter.exception.ResourceNotFoundException;
import com.peter.mappers.TaskMapper;
import com.peter.repositories.TaskListRepository;
import com.peter.repositories.TaskRepository;
import com.peter.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskListRepository taskListRepository;
    private  final TaskMapper mapper;

    @Override
    public List<TaskResponse> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task not found with id: %s", id)));
    }

    @Override
    public List<TaskResponse> findByTaskListId(UUID taskListId) {
        if(!taskListRepository.existsById(taskListId)){
            throw new ResourceNotFoundException(String.format("TaskList not found with id: %s", taskListId));
        }
        return repository.findByTaskListId(taskListId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        TaskList taskList = taskListRepository.findByIdWithTasks(request.taskListId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("TaskList not found with id: %s", request.taskListId())));

        Task task = mapper.toEntity(request);
        taskList.addTask(task);

        return mapper.toResponse(repository.save(task));
    }

    @Override
    public TaskResponse update(UUID id, TaskPatchRequest request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task not found with id: %s", id)));

        if (request.taskListId() != null && !request.taskListId().equals(task.getTaskList().getId())) {
            TaskList oldList = task.getTaskList();
            TaskList newList = taskListRepository.findByIdWithTasks(
                    request.taskListId()
            )
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("TaskList not found with id: %s", request.taskListId())));
            oldList.removeTask(task);
            newList.addTask(task);
        }

        mapper.updateEntity(task, request);
        Task savedTask = repository.save(task);
        return mapper.toResponse(savedTask);
    }

    @Override
    public void delete(UUID id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task not found with id: %s", id)));
        task.getTaskList().removeTask(task);
        repository.delete(task);

    }
}
