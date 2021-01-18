package com.distribution.simulation.entity.simulation;


import com.distribution.simulation.enums.WorkerEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(catalog = "simulation_db", name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private WorkerEnum type;

    private Integer age;

    private Integer capacity;

    private boolean isAvailable;
}
