package com.joanob.yourownboss.admin.productionBuildings;

import com.joanob.yourownboss.admin.productionBuildings.dto.AdminProductionBuildingRequest;
import com.joanob.yourownboss.user.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/production-buildings")
@RequiredArgsConstructor
public class AdminProductionBuildingController {

    private final AdminProductionBuildingService service;

    @PostMapping
    public ResponseEntity<?> createProductionBuilding(
            @RequestBody @Valid AdminProductionBuildingRequest request,
            Authentication authentication
    ) {
        service.createProductionBuilding(request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> modifyProductionBuilding(
            @RequestBody @Valid AdminProductionBuildingRequest request,
            Authentication authentication
    ) {
        service.modifyProductionBuilding(request, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductionBuilding(
            @PathVariable("id") String ProductionBuildingId,
            Authentication authentication
    ) {
        service.deleteProductionBuilding(ProductionBuildingId, (User) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }
}
