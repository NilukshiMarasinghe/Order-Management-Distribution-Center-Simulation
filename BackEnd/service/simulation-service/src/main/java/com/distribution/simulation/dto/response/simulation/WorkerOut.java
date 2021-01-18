package com.distribution.simulation.dto.response.simulation;

import com.distribution.simulation.enums.WorkerEnum;
import lombok.Data;

@Data
public class WorkerOut {
    private Long id;
    private String name;
    private WorkerEnum type;
    private Integer age;
    private Integer capacity;
    private boolean isAvailable;
}
