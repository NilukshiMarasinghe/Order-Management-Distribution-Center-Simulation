package com.demigods.orderManagementService.model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@RequiredArgsConstructor
@Document
public class PackingStation {
    @Id
    private long id;
    private Boolean isAvailable;
}
