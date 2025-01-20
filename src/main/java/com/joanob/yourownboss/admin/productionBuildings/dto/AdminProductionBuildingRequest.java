package com.joanob.yourownboss.admin.productionBuildings.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AdminProductionBuildingRequest(
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
