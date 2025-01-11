package com.capyjella.capydate.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotNull()
        @NotEmpty()
        @NotBlank()
        String title,
        String description,
        // Tasks should have a date or and start and end time
        long date,
        long startTime,
        long endTime,
        String color
) {
}
