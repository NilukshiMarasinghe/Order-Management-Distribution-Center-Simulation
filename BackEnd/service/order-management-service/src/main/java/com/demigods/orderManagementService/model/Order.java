package com.demigods.orderManagementService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private String id;
    private List<OrderItem> items = new ArrayList();;
    private List<OrderItem> pendingItems = new ArrayList();;
    private OrderStatus status;
    private Date createdTime;
    private Date completedTime;
    private long packingStationId;;
    @DBRef
    private List<Task> tasks = new ArrayList();;

    public boolean isOrderComplete(){
        boolean allTasksComplete = true;
        for (Task nextTask : this.tasks) {
            if(!nextTask.getStatus().equals(TaskStatus.COMPLETE)){
                allTasksComplete = false;
            }
        }
        return allTasksComplete && this.pendingItems.isEmpty();
    }

    public void completeOrder(){
        this.status = OrderStatus.COMPLETE;
        this.completedTime = new Date();
    }
}
