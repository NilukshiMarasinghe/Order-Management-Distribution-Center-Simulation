package com.distribution.worker.dto.response.worker;


import com.distribution.worker.enums.WorkerEnum;
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
