package com.peter.repositories;

import com.peter.domain.entities.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

//    @EntityGraph(attributePaths = {"taskList"})
//    Optional<Task> findById(UUID id);
//
//    @EntityGraph(attributePaths = {"taskList"})
//    List<Task> findAll();

    @Query("SELECT t FROM Task t JOIN FETCH t.taskList WHERE t.taskList.id = :taskListId")
    List<Task> findByTaskListId(@Param("taskListId") UUID taskListId);
}
