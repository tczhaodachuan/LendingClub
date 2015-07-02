package org.p2p.lending.club.api.data;

import java.util.Vector;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Graph {
    private final Vector<Vertex> vertexes;
    private final Vector<Edge> edges;

    public Graph() {
        vertexes = new Vector<>();
        edges = new Vector<>();
    }
}
