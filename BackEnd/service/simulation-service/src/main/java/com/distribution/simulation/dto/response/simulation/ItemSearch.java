package com.distribution.simulation.dto.response.simulation;


import lombok.Data;

@Data
public class ItemSearch {

    private Long id;
    private String productId;
    private String name;
    private Integer weight;
    private String supplier;
    private ShelfOut shelf;

    @Data
    public static class ShelfOut {
        private Long id;
        private String label;
        private SectionOut section;

        @Data
        public static class SectionOut {
            private Long id;
            private String sectionId;
        }

    }

}
