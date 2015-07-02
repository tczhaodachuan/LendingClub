package org.p2p.lending.club.api.data;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Vertex {
    private static final Logger LOG = LogManager.getLogger(Vertex.class);
    private final VertexData vertexData;

    public Vertex(VertexData vertexData) {
        this.vertexData = vertexData;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) object;
        if (vertexData == null) {
            return vertex.vertexData == null ? true : false;
        }

        return vertexData.equals(vertex.vertexData);
    }

    @Override
    public int hashCode()
    {
        if(vertexData == null)
        {
            return 0;
        }

        return vertexData.hashCode();
    }
}
