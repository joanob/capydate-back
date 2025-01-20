package com.joanob.yourownboss.productionBuildings;

import com.joanob.yourownboss.common.PageResponse;
import com.joanob.yourownboss.productionBuildings.dto.ProductionBuildingResponse;
import com.joanob.yourownboss.user.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("production-buildings")
@RequiredArgsConstructor
public class ProductionBuildingController {

    private final ProductionBuildingService service;

    @GetMapping
    public ResponseEntity<PageResponse<ProductionBuildingResponse>> getAllProductionBuildings(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "1000", required = false) Integer size,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.getAllproductionBuildings(page, size, (User) authentication.getPrincipal()));
    }
}
