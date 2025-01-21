package com.joanob.yourownboss.productionBuildings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductionProcess {
    @Id
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "production_building_id")
    @JsonBackReference
    private ProductionBuilding productionBuilding;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionProcess subTask = (ProductionProcess) o;
        return Objects.equals(id, subTask.id);
    }
}
