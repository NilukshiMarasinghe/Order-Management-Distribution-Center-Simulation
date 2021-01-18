package com.demigods.orderManagementService.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderGeneratorRegisterRequest {
    private String name;
    private String uri;
}
