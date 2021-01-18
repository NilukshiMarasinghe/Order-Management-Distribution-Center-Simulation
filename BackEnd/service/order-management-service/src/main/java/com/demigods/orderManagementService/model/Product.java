package com.demigods.orderManagementService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    private String name;
    private Double weight;
    private String supplier;
    private String location;
}
