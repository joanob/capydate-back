package com.joanob.yourownboss.admin.productionBuildings.dto;

import com.joanob.yourownboss.productionBuildings.ProductionBuilding;
import com.joanob.yourownboss.productionBuildings.ProductionProcess;
import org.springframework.stereotype.Service;

@Service
public class AdminProductionBuildingMapper {
    public ProductionBuilding toProductionBuilding(AdminProductionBuildingRequest request) {
        ProductionBuilding productionBuilding = new ProductionBuilding();

        productionBuilding.setId(request.id());
        productionBuilding.setName(request.name());

        for (AdminProductionProcessRequest processRequest : request.processes()) {
            productionBuilding.addProcess(ProductionProcess.builder()
                    .id(processRequest.id())
                    .name(processRequest.name())
                    .build());
        }

        return productionBuilding;
    }
}
