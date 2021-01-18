package com.distribution.simulation.entity.simulation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(catalog = "simulation_db", name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectionId;

    @ManyToOne
    @JoinColumn(name = "aisle_id")
    private Aisle aisle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Shelf> shelves;


}
