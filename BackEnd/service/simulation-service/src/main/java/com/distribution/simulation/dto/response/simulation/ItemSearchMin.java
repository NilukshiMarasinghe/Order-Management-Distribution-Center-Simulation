package com.distribution.simulation.dto.response.simulation;


import lombok.Data;

//Minified response of  Items - As per the Technical Overview Doc 1 ppt
@Data
public class ItemSearchMin {

    private Long id;
    private String productId;
    private String name;
    private Integer weight;
    private String supplier;
    private String location;

    public ItemSearchMin() {
    }

    public ItemSearchMin(Long id, String productId, String name, Integer weight, String supplier, String location) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.supplier = supplier;
        this.location = location;
    }
}
