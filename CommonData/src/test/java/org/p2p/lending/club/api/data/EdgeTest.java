package org.p2p.lending.club.api.data;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.VertexString;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by tczhaodachuan on 7/8/2015.
 */
public class EdgeTest {

    private Edge first;
    private Edge second;

    @Before
    public void setUp() {
        first = new Edge(new Vertex(new VertexString("A")), new Vertex(new VertexString("B")));
        second = new Edge(new Vertex(new VertexString("B")), new Vertex(new VertexString("C")));
    }

    @Test
    public void testEquals() throws Exception {
        Edge anotherFirst = new Edge(new Vertex(new VertexString("A")), new Vertex(new VertexString("B")));
        assertEquals(first, anotherFirst);
        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() throws Exception {
        Set<Edge> set = new HashSet<>();
        set.add(first);
        set.add(second);
        Edge anotherFirst = new Edge(new Vertex(new VertexString("A")), new Vertex(new VertexString("B")));
        assertTrue(set.contains(first));
        assertTrue(set.contains(second));
        assertTrue(set.contains(anotherFirst));

        Edge edge = new Edge(null, new Vertex(new VertexString("B")));
        assertTrue(edge.hashCode() == 0);
        edge = new Edge(new Vertex(new VertexString("B")), null);
        assertTrue(edge.hashCode() == 0);
    }

    @Test
    public void testToString() throws Exception {
        System.out.println("first.toString() = " + first.toString());
        String str = "A ==> B";
        assertTrue(str.equals(first.toString()));
    }
}