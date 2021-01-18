package com.demigods.orderManagementService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Document
public class Task{
    @Id
    private String id;
    @NonNull
    private long workerId;
    private String orderId;
    private long packingStationId;
    private List<OrderItem> items;
    private TaskStatus status;
}


