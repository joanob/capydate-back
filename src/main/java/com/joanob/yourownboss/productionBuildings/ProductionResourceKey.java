package com.joanob.yourownboss.productionBuildings;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductionResourceKey {
    private String processId;
    private String resourceId;
    private boolean isOutput; // Is input or output

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionResourceKey that = (ProductionResourceKey) o;
        return Objects.equals(processId, that.processId) &&
                Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(isOutput, that.isOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, resourceId, isOutput);
    }
}
