package com.joanob.yourownboss.productionBuildings.dto;

import com.joanob.yourownboss.productionBuildings.ProductionBuilding;
import org.springframework.stereotype.Service;

@Service
public class ProductionBuildingMapper {
    public ProductionBuildingResponse toProductionBuildingResponse(ProductionBuilding productionBuilding) {
        return ProductionBuildingResponse.builder()
                .id(productionBuilding.getId())
                .name(productionBuilding.getName())
                .processes(productionBuilding.getProcesses())
                .build();
    }
}
