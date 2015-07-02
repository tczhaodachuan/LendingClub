package org.p2p.lending.club.api.data;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Edge {
    private final Vertex v;
    private final Vertex m;

    public Edge(Vertex v, Vertex m) {
        this.v = v;
        this.m = m;
    }
}
