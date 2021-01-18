package com.demigods.orderManagementService.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productId;
    private String name;
    private Double weight;
    private String supplier;
    private String location;
}
