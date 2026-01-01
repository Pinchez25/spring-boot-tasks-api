    package com.peter.domain.entities;

    import jakarta.persistence.*;
    import lombok.*;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.UUID;

    @Entity
    @Table(name = "task_lists")
    @EntityListeners(AuditingEntityListener.class)
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public class TaskList {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;

        @Column(name = "title", nullable = false)
        private String title;

        @Column(name = "description")
        private String description;

        @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
        @Builder.Default
        private List<Task> tasks = new ArrayList<>();

        @CreatedDate
        @Column(name = "created", nullable = false, updatable = false)
        private LocalDateTime created;

        @LastModifiedDate
        @Column(name = "updated", nullable = false)
        private LocalDateTime updated;

        public void addTask(Task task){
            if (task == null) return;
            tasks.add(task);
            task.setTaskList(this);
        }
        public  void removeTask(Task task){
            if (task == null) return;
            tasks.remove(task);
            task.setTaskList(null);
        }

    }
