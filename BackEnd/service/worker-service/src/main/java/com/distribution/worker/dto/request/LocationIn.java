package com.distribution.worker.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class LocationIn {

    private Long packingStationId;
    private List<String> locations;

    public LocationIn() {
    }

    public LocationIn(Long packingStationId, List<String> locations) {
        this.packingStationId = packingStationId;
        this.locations = locations;
    }
}
