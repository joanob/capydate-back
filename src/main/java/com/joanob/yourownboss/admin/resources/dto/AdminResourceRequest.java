package com.joanob.yourownboss.admin.resources.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AdminResourceRequest(
        @NotNull()
        @NotEmpty()
        @NotBlank()
        String id,

        @NotNull()
        @NotEmpty()
        @NotBlank()
        String name
) {
}
