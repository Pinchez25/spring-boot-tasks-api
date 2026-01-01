package com.peter.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskListRequest(
        @NotBlank(message = "Title is required") String title,
        String description
) {
}
