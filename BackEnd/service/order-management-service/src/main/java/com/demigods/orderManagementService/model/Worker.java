package com.demigods.orderManagementService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Worker {
    private long id;
    private String name;
    private Boolean isAvailable;
    private Integer age;
    private double capacity;
    private double availableCapacity;
}
