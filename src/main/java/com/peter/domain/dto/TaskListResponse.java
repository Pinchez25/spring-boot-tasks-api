package com.peter.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskListResponse(
        UUID id,
        String title,
        String description,
        LocalDateTime created,
        LocalDateTime updated
) {
}
