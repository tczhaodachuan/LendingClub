package org.p2p.lending.club.api.data;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.VertexString;

import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by tczhaodachuan on 7/8/2015.
 */
public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        VertexString vsA = new VertexString("A");
        Vertex vA = new Vertex(vsA);
        VertexString vsB = new VertexString("B");
        Vertex vB = new Vertex(vsB);
        VertexString vsC = new VertexString("C");
        Vertex vC = new Vertex(vsC);
        VertexString vsD = new VertexString("D");
        Vertex vD = new Vertex(vsD);
        graph.addVertex(vA);
        graph.addVertex(vB);
        graph.addVertex(vC);
        graph.addVertex(vD);
        graph.addEdge(vA, vC);
        graph.addEdge(vC, vD);
        graph.addEdge(vC, vB);

    }

    @Test
    public void testAddVertex() throws Exception {
        Vector<Vertex> vertexes = graph.getVertexes();
        VertexString vsB = new VertexString("B");
        Vertex vB = new Vertex(vsB);
        int index = vertexes.indexOf(vB);
        System.out.println("index = " + index);
    }

    @Test
    public void testAddEdge() throws Exception {

    }

    @Test
    public void testGetAdjMatrix() throws Exception {

    }

    @Test
    public void testPrintAdjMatrix() throws Exception {
        graph.printAdjMatrix();
    }
}