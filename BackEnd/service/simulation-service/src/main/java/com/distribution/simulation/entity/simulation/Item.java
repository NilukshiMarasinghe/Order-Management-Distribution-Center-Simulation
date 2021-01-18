package com.distribution.simulation.entity.simulation;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(catalog = "simulation_db", name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    private String name;

    private Integer weight;

    private String supplier;

    @OneToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

}
