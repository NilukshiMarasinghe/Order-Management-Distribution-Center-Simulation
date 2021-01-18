package com.demigods.orderManagementService.dto.request.order;

import com.demigods.orderManagementService.dto.common.OrderItemDto;
import com.demigods.orderManagementService.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class OrderCreateRequest {

    @NotNull
    private String id;

    @NotNull
    private List<OrderItemDto> items;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Date createdTime;
}
