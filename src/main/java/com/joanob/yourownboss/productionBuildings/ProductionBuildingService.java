package com.joanob.yourownboss.productionBuildings;

import com.joanob.yourownboss.common.PageResponse;
import com.joanob.yourownboss.productionBuildings.dto.ProductionBuildingMapper;
import com.joanob.yourownboss.productionBuildings.dto.ProductionBuildingResponse;
import com.joanob.yourownboss.user.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionBuildingService {

    private final ProductionBuildingRepository repository;
    private final ProductionBuildingMapper mapper;

    public PageResponse<ProductionBuildingResponse> getAllproductionBuildings(Integer page, Integer size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductionBuilding> productionBuildings = repository.findAll(pageable);
        List<ProductionBuildingResponse> resourceResponse = productionBuildings.stream().map(mapper::toProductionBuildingResponse).toList();
        return new PageResponse<>(
                resourceResponse,
                productionBuildings.getNumber(),
                productionBuildings.getSize(),
                productionBuildings.getTotalElements(),
                productionBuildings.getTotalPages(),
                productionBuildings.isFirst(),
                productionBuildings.isLast()
        );
    }
}
