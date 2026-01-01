package com.peter.services.imp;

import com.peter.domain.dto.TaskListRequest;
import com.peter.domain.dto.TaskListResponse;
import com.peter.domain.entities.TaskList;
import com.peter.exception.ResourceNotFoundException;
import com.peter.mappers.TaskListMapper;
import com.peter.repositories.TaskListRepository;
import com.peter.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository repository;
    private final TaskListMapper mapper;

    @Override
    public List<TaskListResponse> getAllTaskLists() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TaskListResponse findById(UUID id) {

        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TaskList not found with id: " + id
                )));
    }

    @Override
    public TaskListResponse create(TaskListRequest request) {
        TaskList list = repository.save(mapper.toEntity(request));
        return mapper.toResponse(list);
    }

    @Override
    public TaskListResponse update(UUID id, TaskListRequest request) {
       TaskList taskList = repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(String.format("Task List not found with id: %s", id)));
       mapper.updateEntity(taskList, request);

       TaskList updatedTaskList = repository.save(taskList);

       return mapper.toResponse(updatedTaskList);
    }

    @Override
    public void delete(UUID id) {
        if ( !repository.existsById(id) ) {
            throw  new ResourceNotFoundException(String.format("Task List not found with id: %s", id));
        }
        repository.deleteById(id);
    }
}
