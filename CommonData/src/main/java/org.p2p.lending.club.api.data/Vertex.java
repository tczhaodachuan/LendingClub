package org.p2p.lending.club.api.data;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Vertex {
    private static final Logger LOG = LogManager.getLogger(Vertex.class);
    protected final VertexData vertexData;
    private boolean visit = false;

    public Vertex(VertexData vertexData) {
        this.vertexData = vertexData;
    }

    public void unvist() {
        visit = false;
    }

    public void visited() {
        visit = true;
    }

    public boolean isVisit() {
        return visit;
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
            return vertex.vertexData == null;
        }

        return vertexData.equals(vertex.vertexData);
    }

    @Override
    public int hashCode() {
        if (vertexData == null) {
            return 0;
        }

        return Objects.hashCode(vertexData);
    }
}
