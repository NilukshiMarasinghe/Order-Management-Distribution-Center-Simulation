package com.distribution.worker.entity.worker;



import com.distribution.worker.enums.WorkerEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(catalog = "worker_db", name = "worker")
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
