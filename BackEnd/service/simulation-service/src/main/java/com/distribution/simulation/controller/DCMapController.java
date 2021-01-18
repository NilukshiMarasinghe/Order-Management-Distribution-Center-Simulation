package com.distribution.simulation.controller;


import com.distribution.simulation.componant.DCMapLayout;
import com.distribution.simulation.dto.request.LocationIn;
import com.distribution.simulation.dto.response.simulation.DcMapOut;
import com.distribution.simulation.dto.response.simulation.PackingStationSearch;
import com.distribution.simulation.dto.response.wrapper.ListResponseWrapper;
import com.distribution.simulation.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.simulation.entity.simulation.PackingStation;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.service.MapService;
import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class DCMapController {

    @Autowired
    private MapService mapService;

    private static final Logger logger = Logger.getLogger(DCMapController.class.getName());

    @Autowired
    DCMapLayout mapLayout;


    @GetMapping("${app.endpoint.getDcMap}")
    public ResponseEntity<SimpleResponseWrapper<DcMapOut>> getDCMap() {

        DefaultDirectedGraph<String, DefaultEdge> graph = mapLayout.fetchDCMap();

        List<String> vertices = graph.vertexSet().stream()
                .map(vertex -> vertex.toString()).collect(Collectors.toList());

        List<String> edges = graph.edgeSet().stream()
                .map(defaultEdge -> defaultEdge.toString()).collect(Collectors.toList());

        DcMapOut out = new DcMapOut(vertices, edges);

        return new ResponseEntity<SimpleResponseWrapper<DcMapOut>>(
                new SimpleResponseWrapper<DcMapOut>(out), HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.getAllVertices}")
    public ResponseEntity<ListResponseWrapper<String>> getVertices() {

        DefaultDirectedGraph<String, DefaultEdge> graph = mapLayout.fetchDCMap();
        List<String> vertices = graph.vertexSet().stream()
                .map(vertex -> vertex.toString()).collect(Collectors.toList());

        return new ResponseEntity<ListResponseWrapper<String>>(
                new ListResponseWrapper<String>(vertices), HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.getOppositeVertices}")
    public ResponseEntity<ListResponseWrapper<String>> getOppositeVertices(@PathVariable String vertex) {

        if (vertex == null) {
            throw new ComplexValidationException("getOpposite", "vertexNull");
        }

        Graph<String, DefaultEdge> graph = mapLayout.fetchDCMap();

        if (!graph.vertexSet().contains(vertex)) {
            throw new ComplexValidationException("getOpposite", "invalidVertexCoordinate");
        }

        List<String> opposites = new ArrayList<>();
        for (DefaultEdge e : graph.outgoingEdgesOf(vertex)) {
            String w = Graphs.getOppositeVertex(graph, e, vertex);
            System.out.println(w);
            opposites.add(w);
        }

        return new ResponseEntity<ListResponseWrapper<String>>(
                new ListResponseWrapper<String>(opposites), HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.getPackingStations}")
    public ResponseEntity<ListResponseWrapper<PackingStation>> getPackingStations() {
        List<PackingStation> stations = this.mapService.getPackingStations();
        return new ResponseEntity<ListResponseWrapper<PackingStation>>(
                new ListResponseWrapper<PackingStation>(stations), HttpStatus.CREATED);
    }

    @PostMapping("${app.endpoint.getShortestPath}")
    public ResponseEntity<ListResponseWrapper<String>> getShortestPath(@Validated @RequestBody LocationIn in) {

        PackingStation pa = this.mapService.getPackingStationById(in.getPackingStationId());

        List<String> shortestPaths = new ArrayList<>();
        try {
            List<String> locations = in.getLocations();

            DijkstraShortestPath<String, DefaultEdge> dp = new DijkstraShortestPath<>(mapLayout.fetchDCMap());
            // determine shortest path from first section to last section before packing station
            for (int i = 0; i < locations.size() - 1; i++) {
                int next = i + 1;
                List<String> paths = dp.getPath(locations.get(i), locations.get(next)).getEdgeList().stream()
                        .map(defaultEdge -> {
                            return defaultEdge.toString();
                        }).collect(Collectors.toList());

                shortestPaths.addAll(paths);
            }

            // determine the shortest path from last section to packing station
            List<String> pathToPack = dp.getPath(locations.get(locations.size() - 1), pa.getName()).getEdgeList().stream()
                    .map(defaultEdge -> {
                        return defaultEdge.toString();
                    }).collect(Collectors.toList());

            shortestPaths.addAll(pathToPack);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<ListResponseWrapper<String>>(
                new ListResponseWrapper<String>(shortestPaths), HttpStatus.CREATED);
    }


}
