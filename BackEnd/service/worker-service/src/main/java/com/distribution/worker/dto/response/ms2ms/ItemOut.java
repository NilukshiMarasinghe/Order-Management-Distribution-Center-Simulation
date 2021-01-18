package com.distribution.worker.dto.response.ms2ms;


import lombok.Data;

@Data
public class ItemOut {
    public String status;
    public Integer statusCode;
    private ProductOut content;


    @Data
    public static class ProductOut {
        private Long id;
        private String productId;
        private String name;
        private Integer weight;
        private String supplier;
        private String location;
    }
}
