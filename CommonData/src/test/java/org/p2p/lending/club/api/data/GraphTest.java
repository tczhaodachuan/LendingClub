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
    private Graph cycleGraph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        cycleGraph = new Graph();
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


        cycleGraph.addVertex(vA);
        cycleGraph.addVertex(vB);
        cycleGraph.addVertex(vC);
        cycleGraph.addVertex(vD);
        cycleGraph.addEdge(vA, vC);
        cycleGraph.addEdge(vC, vD);
        cycleGraph.addEdge(vC, vB);
        cycleGraph.addEdge(vB, vD);
        cycleGraph.addEdge(vD, vA);

    }

    @Test
    public void testCycle()
    {
        assertTrue(cycleGraph.isCycleExisting());
        assertFalse(graph.isCycleExisting());
    }

    @Test
    public void testAddVertex() throws Exception {
        Vector<Vertex> vertexes = graph.getVertexes();
        VertexString vsB = new VertexString("B");
        Vertex vB = new Vertex(vsB);
        int index = vertexes.indexOf(vB);
        assertTrue(index == 1);
    }

    @Test
    public void testAddEdge() throws Exception {
        Vertex vA = new Vertex(new VertexString("A"));
        Vertex vC = new Vertex(new VertexString("C"));
        Edge edge = new Edge(vA, vC);
        Vector<Edge> edges = graph.getEdges();
        int index = edges.indexOf(edge);
        assertTrue(index == 0);
    }

    @Test
    public void testGetAdjMatrix() throws Exception {
        int[][] adjMatrix = graph.getAdjMatrix();
        assertTrue(adjMatrix[0][2] == 1);
        assertTrue(adjMatrix[2][1] == 1);
        assertTrue(adjMatrix[2][3] == 1);
    }

    @Test
    public void testFindVertex()
    {
        Vertex vC = graph.findVertex(new VertexString("C"));
        assertNotNull(vC);

        Vertex vE = graph.findVertex(new VertexString("E"));
        assertNull(vE);
    }

    @Test
    public void testPrintAdjMatrix() throws Exception {
        graph.printAdjMatrix();
    }
}