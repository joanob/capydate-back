package com.joanob.yourownboss.admin.productionBuildings;

import com.joanob.yourownboss.admin.productionBuildings.dto.AdminProductionBuildingMapper;
import com.joanob.yourownboss.admin.productionBuildings.dto.AdminProductionBuildingRequest;
import com.joanob.yourownboss.productionBuildings.ProductionBuilding;
import com.joanob.yourownboss.productionBuildings.ProductionBuildingRepository;
import com.joanob.yourownboss.productionBuildings.ProductionProcessRepository;
import com.joanob.yourownboss.user.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductionBuildingService {

    private final ProductionBuildingRepository buildingRepository;
    private final ProductionProcessRepository processRepository;
    private final AdminProductionBuildingMapper mapper;

    public void createProductionBuilding(@Valid AdminProductionBuildingRequest request, User user) {
        ProductionBuilding productionBuilding = mapper.toProductionBuilding(request);

        buildingRepository.save(productionBuilding);
    }

    public void modifyProductionBuilding(@Valid AdminProductionBuildingRequest request, User user) {
        ProductionBuilding productionBuilding = buildingRepository.findById(request.id()).orElseThrow(() -> new EntityNotFoundException("ProductionBuilding " + request.id() + " not found"));

        ProductionBuilding modifiedProductionBuilding = mapper.toProductionBuilding(request);
        productionBuilding.setName(modifiedProductionBuilding.getName());
        productionBuilding.modifyAllProcesses(modifiedProductionBuilding.getProcesses());

        buildingRepository.save(productionBuilding);
    }

    public void deleteProductionBuilding(String productionBuildingId, User user) {
        ProductionBuilding productionBuilding = buildingRepository.findById(productionBuildingId).orElseThrow(() -> new EntityNotFoundException("ProductionBuilding " + productionBuildingId + " not found"));
        buildingRepository.delete(productionBuilding);
    }
}
