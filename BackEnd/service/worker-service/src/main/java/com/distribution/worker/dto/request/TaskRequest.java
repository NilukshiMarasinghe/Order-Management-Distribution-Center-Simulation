package com.distribution.worker.dto.request;

import com.distribution.worker.enums.TaskStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TaskRequest {

    @NotBlank(message = "taskId.empty")
    private String id;
    @NotBlank(message = "workerId.empty")
    private String workerId;
    @NotBlank(message = "orderId.empty")
    private String orderId;
    @NotBlank(message = "packingStationId.empty")
    private String packingStationId;
    private List<OrderItemIn> items;
    private String status;


    @Data
    public static class OrderItemIn {
        private ItemIn product;
        private int qty;

        @Data
        public static class ItemIn {
            @NotBlank(message = "productId.empty")
            private String productId;
            private String name;
            private Double weight;
            private String supplier;
            @NotBlank(message = "location.empty")
            private String location;
        }
    }
}
