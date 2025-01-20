package com.joanob.yourownboss.admin.productionBuildings;

import com.joanob.yourownboss.admin.productionBuildings.dto.AdminProductionBuildingRequest;
import com.joanob.yourownboss.productionBuildings.ProductionBuilding;
import com.joanob.yourownboss.productionBuildings.ProductionBuildingRepository;
import com.joanob.yourownboss.user.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductionBuildingService {

    private final ProductionBuildingRepository repository;

    public void createProductionBuilding(@Valid AdminProductionBuildingRequest request, User user) {
        ProductionBuilding productionBuilding = ProductionBuilding.builder()
                .id(request.id())
                .name(request.name())
                .build();
        repository.save(productionBuilding);
    }

    public void modifyProductionBuilding(@Valid AdminProductionBuildingRequest request, User user) {
        ProductionBuilding productionBuilding = repository.findById(request.id()).orElseThrow(() -> new EntityNotFoundException("ProductionBuilding " + request.id() + " not found"));

        productionBuilding.setName(request.name());
        repository.save(productionBuilding);
    }

    public void deleteProductionBuilding(String productionBuildingId, User user) {
        ProductionBuilding productionBuilding = repository.findById(productionBuildingId).orElseThrow(() -> new EntityNotFoundException("ProductionBuilding " + productionBuildingId + " not found"));
        repository.delete(productionBuilding);
    }
}
