package com.demigods.orderManagementService.dto.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkerResponse {

    private long id;

    private String name;

    private Integer age;

    private Double capacity;

    private Boolean isAvailable;
}
