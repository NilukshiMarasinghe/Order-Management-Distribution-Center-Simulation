package com.distribution.worker.entity.worker;


import com.distribution.worker.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(catalog = "worker_db", name = "task_record")
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskId;
    private Long workerId;
    private String orderId;
    private String packingStationId;
    private String shortedPath;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskRecord", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> items;
    private String status;
}
