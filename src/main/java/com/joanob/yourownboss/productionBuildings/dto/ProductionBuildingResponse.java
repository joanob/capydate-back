package com.joanob.yourownboss.productionBuildings.dto;

import com.joanob.yourownboss.productionBuildings.ProductionProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionBuildingResponse {
    private String id;
    private String name;
    private List<ProductionProcess> processes;
}
