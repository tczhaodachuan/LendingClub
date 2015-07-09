package org.p2p.lending.club.api.data.impl;

import org.p2p.lending.club.api.data.VertexData;

import java.util.Objects;

/**
 * Created by tczhaodachuan on 7/8/2015.
 */
public class VertexString implements VertexData {
    protected final String value;

    public VertexString(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }

    @Override
    public boolean equals(VertexData vertexData) {
        if (vertexData == null) {
            return false;
        }

        if (getClass() != vertexData.getClass()) {
            return false;
        }

        VertexString vertexString = (VertexString) vertexData;
        return value.equals(vertexString.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}
