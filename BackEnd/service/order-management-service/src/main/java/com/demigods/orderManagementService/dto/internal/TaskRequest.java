package com.demigods.orderManagementService.dto.internal;

import com.demigods.orderManagementService.model.OrderItem;
import com.demigods.orderManagementService.model.TaskStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskRequest{
    private String id;
    private long workerId;
    private String orderId;
    private String packingStationId;
    private List<OrderItem> items;
    private TaskStatus status;
}