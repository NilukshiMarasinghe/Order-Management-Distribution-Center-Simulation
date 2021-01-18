package com.demigods.orderManagementService.dto.request.task;

import com.demigods.orderManagementService.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskUpdateRequest {
    @NotNull
    private String id;
    @NotNull
    private TaskStatus status;
}

