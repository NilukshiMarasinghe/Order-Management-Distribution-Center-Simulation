package com.distribution.worker.dto.response.worker;


import lombok.Data;

import java.util.List;

@Data
public class TaskRequestOut {

    private String id;
    private String taskId;
    private String workerId;
    private String orderId;
    private String packingStationId;
    private List<OrderItemOut> items;
    private String shortedPath;

    @Data
    public static class OrderItemOut {
        private String productId;
        private int qty;
    }
}
