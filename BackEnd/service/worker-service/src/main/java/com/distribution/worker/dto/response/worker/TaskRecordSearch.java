package com.distribution.worker.dto.response.worker;


import lombok.Data;

import java.util.List;

@Data
public class TaskRecordSearch {

    private String id;
    private String taskId;
    private String workerId;
    private String orderId;
    private String packingStationId;
    private List<OrderItemOut> items;
    private String shortedPath;
    private String status;

    @Data
    public static class OrderItemOut {

        private ItemOut product;
        private int qty;

        @Data
        public static class ItemOut {
            private Long id;
            private String productId;
            private String name;
            private Integer weight;
            private String supplier;
            private String location;
        }

    }
}
