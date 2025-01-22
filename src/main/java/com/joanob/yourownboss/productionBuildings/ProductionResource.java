package com.joanob.yourownboss.productionBuildings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ProductionResourceKey.class)
public class ProductionResource {
    @Id
    private String processId;
    @Id
    private String resourceId;
    @Id
    private boolean isOutput;

    private int quantity;

    @ManyToOne
    @MapsId("processId") // Maps to the processId in the composite key
    @JoinColumn(name = "process_id")
    @JsonBackReference
    private ProductionProcess process;

    /*@ManyToOne
    @MapsId("resourceId") // Maps to the resourceId in the composite key
    @JoinColumn(name = "resource_id")
    private Resource resource;*/
}
