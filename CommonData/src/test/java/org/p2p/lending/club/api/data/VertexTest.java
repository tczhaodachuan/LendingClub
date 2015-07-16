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
public class VertexTest {
    private Vertex first;
    private Vertex second;

    @Before
    public void setUp() throws Exception {
        first = new Vertex(new VertexString("first"));
        second = new Vertex(new VertexString("second"));
    }

    @Test
    public void testIsVisit() throws Exception {
        assertFalse(first.isVisit());
        first.visited();
        assertTrue(first.isVisit());
        first.unvist();
        assertFalse(first.isVisit());
    }

    @Test
    public void testEquals() throws Exception {
        Vertex anotherFirst = new Vertex(new VertexString("first"));
        assertEquals(first, anotherFirst);
        assertNotEquals(first, second);
    }

    @Test
    public void testHashCode() throws Exception {
        Vertex anotherFirst = new Vertex(new VertexString("first"));
        Vertex none = new Vertex(null);
        assertEquals(none.hashCode(), 0);

        Set<Vertex> set = new HashSet<>();
        set.add(first);
        set.add(second);

        assertTrue(set.contains(first));
        assertTrue(set.contains(second));
        // since the VertexString applies the String hashCode, same string shares the same hashcode
        assertTrue(set.contains(anotherFirst));
    }
}