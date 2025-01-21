package com.joanob.yourownboss.productionBuildings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductionBuilding {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "productionBuilding", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductionProcess> processes = new ArrayList<>();

    public void addProcess(ProductionProcess process) {
        process.setProductionBuilding(this);
        processes.add(process);
    }

    public void removeProcess(ProductionProcess process) {
        process.setProductionBuilding(null);
        processes.remove(process);
    }

    public void modifyAllProcesses(List<ProductionProcess> processes) {
        // Add new processes
        for (ProductionProcess process : processes) {
            if (this.processes.stream().filter(p -> p.getId().contentEquals(process.getId())).count() == 0) {
                this.addProcess(process);
            }
        }
    }
}
