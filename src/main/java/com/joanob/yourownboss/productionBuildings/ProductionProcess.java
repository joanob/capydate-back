package com.joanob.yourownboss.productionBuildings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

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
    private long duration; // in miliseconds

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "production_building_id")
    @JsonBackReference
    private ProductionBuilding productionBuilding;

    @OneToMany(mappedBy = "processId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("is_output = false")
    @JsonManagedReference
    private List<ProductionResource> input = new ArrayList<>();

    @OneToMany(mappedBy = "processId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("is_output = true")
    @JsonManagedReference
    private List<ProductionResource> output = new ArrayList<>();
}
