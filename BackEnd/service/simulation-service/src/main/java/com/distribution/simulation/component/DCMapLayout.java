package com.distribution.simulation.componant;


import com.distribution.simulation.entity.simulation.Aisle;
import com.distribution.simulation.repository.simulation.AisleRepo;


import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DCMapLayout {

    @Autowired
    private AisleRepo aisleRepo;

    private static final Logger logger = Logger.getLogger(DCMapLayout.class.getName());

    public DefaultDirectedGraph<String, DefaultEdge> fetchDCMap() {

        // Fetch Aisle,sections which contains vertices and edges data
        List<Aisle> aisles = this.aisleRepo.findAll();

        DefaultDirectedGraph<String, DefaultEdge> map = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Registering all the Vertices and Packing Stations to Graph Structure
        aisles.forEach(a -> {
            a.getSections().forEach(section -> {
                map.addVertex(section.getSectionId());
            });
            map.addVertex(a.getPackingStation().getName());
            // logger.info("NAME : " + a.getName());
        });


        // Registering Horizontal moments through Vertices
        aisles.forEach(aisle -> {
            List<String> secStrs = aisle.getSections().stream().
                    map(section -> section.getSectionId()).collect(Collectors.toList());
            int rounds = secStrs.size() - 1;
            int initVal = 0;
            for (int a = initVal; a < rounds; a++) {
                String from = secStrs.get(a);
                String to = secStrs.get(a + 1);
                //logger.info("from - " + from + " to - " + to);

                // LEFT to RIGHT direction
                map.addEdge(from, to);
                // RIGHT to LEFT direction
                map.addEdge(to, from);
            }
            // logger.info("from - " + secStrs.get(rounds) + " to - " + aisle.getPackingStation().getName());
            // LEFT to RIGHT direction
            map.addEdge(secStrs.get(rounds), aisle.getPackingStation().getName());
            // RIGHT to LEFT direction
            map.addEdge(aisle.getPackingStation().getName(), secStrs.get(rounds));
        });


        // Registering Vertical moments through Vertices
        for (int i = 0; i < aisles.size(); i++) {
            for (int a = 1; a < aisles.size(); a++) {
                String top = "a" + a + "." + i;
                int next = a + 1;
                String bottom = "a" + next + "." + i;
                //logger.info(top + " -> " + bottom);
                // TOP  to BOTTOM direction
                map.addEdge(top, bottom);
                // BOTTOM to TOP direction
                map.addEdge(bottom, top);
            }
        }

        // Vertical edges between Packing Stations
        for (int i = 1; i < aisles.size(); i++) {
            int next = i + 1;
            // LEFT to RIGHT direction
            map.addEdge("PA" + i, "PA" + next);
            // RIGHT to LEFT direction
            map.addEdge("PA" + next, "PA" + i);
        }

//        map.edgeSet().forEach(defaultEdge -> {
//            logger.info(defaultEdge.toString());
//        });


        return map;
    }


}
