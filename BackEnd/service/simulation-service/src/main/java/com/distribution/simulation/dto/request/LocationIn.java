package com.distribution.simulation.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class LocationIn {

    private Long packingStationId;
    private List<String> locations;
}
