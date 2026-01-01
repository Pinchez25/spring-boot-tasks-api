package com.peter.repositories;

import com.peter.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    @Query("SELECT tl FROM TaskList tl LEFT JOIN FETCH tl.tasks WHERE tl.id = :id")
    Optional<TaskList> findByIdWithTasks(@Param("id") UUID id);
}
