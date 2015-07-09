package org.p2p.lending.club.api.data.impl;

import org.p2p.lending.club.api.data.VertexData;

/**
 * Created by tczhaodachuan on 7/8/2015.
 */
public class VertexString implements VertexData {
    protected final String value;

    public VertexString(String value) {
        if(value == null)
        {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }

    @Override
    public boolean equals(VertexData vertexData) {
        if(vertexData == null)
        {
            return false;
        }

        if(getClass() != vertexData.getClass())
        {
            return false;
        }

        VertexString vertexString = (VertexString) vertexData;
        return value.equals(vertexString.value);
    }

    @Override
    public int compares(VertexData vertexData) {
        if(vertexData == null)
        {
            return 1;
        }

        if(getClass() != vertexData.getClass())
        {
            return -1;
        }

        VertexString vertexString = (VertexString) vertexData;
        return value.compareTo(vertexString.value);
    }

    @Override
    public String toString()
    {
        return value.toString();
    }
}
