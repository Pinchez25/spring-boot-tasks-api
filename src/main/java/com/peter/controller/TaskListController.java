package com.peter.controller;

import com.peter.domain.dto.TaskListRequest;
import com.peter.domain.dto.TaskListResponse;
import com.peter.services.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService service;

    @GetMapping
    public ResponseEntity<List<TaskListResponse>> getAllTaskLists() {
        return ResponseEntity.ok(service.getAllTaskLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListResponse> getTaskListById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> createTaskList(
            @Valid @RequestBody TaskListRequest request) {
        TaskListResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListResponse> updateTaskList(
            @PathVariable UUID id,
            @Valid @RequestBody TaskListRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
