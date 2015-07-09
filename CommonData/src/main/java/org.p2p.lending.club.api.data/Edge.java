package org.p2p.lending.club.api.data;

import java.util.Objects;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Edge {
    protected final Vertex v;
    protected final Vertex m;

    public Edge(Vertex v, Vertex m) {
        this.v = v;
        this.m = m;
    }

    @Override
    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        }

        if(getClass() != object.getClass())
        {
            return false;
        }

        Edge edge = (Edge) object;
        if(v == edge.v || v.equals(edge.v))
        {
            return m == edge.m || m.equals(edge.m);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        if(v == null || m == null)
        {
            return 0;
        }

        return Objects.hash(v.vertexData, m.vertexData);
    }

    @Override
    public String toString()
    {
        return v.vertexData.toString() + " ==> " + m.vertexData.toString();
    }
}
