package com.distribution.simulation.dto.response.simulation;


import lombok.Data;

import java.util.List;

@Data
public class DcMapOut {

    private List<String> vertices;
    private List<String> edges;

    public DcMapOut() {
    }

    public DcMapOut(List<String> vertices, List<String> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
}
