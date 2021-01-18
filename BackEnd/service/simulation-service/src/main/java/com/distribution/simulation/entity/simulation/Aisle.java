package com.distribution.simulation.entity.simulation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(catalog = "simulation_db", name = "aisle")
public class Aisle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aisle", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Section> sections;

    @OneToOne
    @JoinColumn(name = "packing_station_id")
    private PackingStation packingStation;

}
