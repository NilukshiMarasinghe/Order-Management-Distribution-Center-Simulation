package com.demigods.orderManagementService.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private ProductDto product;
    private int qty;
}
