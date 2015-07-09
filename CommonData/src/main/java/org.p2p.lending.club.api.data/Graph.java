package org.p2p.lending.club.api.data;

import com.sun.javafx.binding.StringFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Vector;

/**
 * Created by tczhaodachuan on 7/1/2015.
 */
public class Graph {
    private static final Logger LOG = LogManager.getLogger();
    private final Vector<Vertex> vertexes;
    private final Vector<Edge> edges;

    public Graph() {
        vertexes = new Vector<>();
        edges = new Vector<>();
    }

    public int addVertex(final Vertex v) {
        if (!vertexes.contains(v)) {
            vertexes.add(v);
        } else {
            LOG.warn("Vertex {} has been added.", v.toString());
        }

        return vertexes.size();
    }

    public void addEdge(final Vertex v, final Vertex m) {
        Edge edge = new Edge(v, m);
        if (!edges.contains(edge)) {
            edges.add(edge);
        } else {
            LOG.warn("Edge {} has been added.", edge.toString());
        }
    }

    public Vector<Vertex> getVertexes() {
        return vertexes;
    }

    public Vector<Edge> getEdges() {
        return edges;
    }

    public boolean isCycleExisting()
    {
        boolean result = dfs(vertexes.elementAt(0));
        unvisitVertexes();
        return result;
    }

    private void unvisitVertexes()
    {
        vertexes.forEach(vertex -> vertex.unvist());
    }

    private boolean dfs(Vertex v)
    {
        if(v.isVisit())
        {
            return true;
        }
        else
        {
            v.visited();
            Vector<Vertex> neighbors = getNeighbors(v);
            for(Vertex neighbor : neighbors)
            {
                if (dfs(neighbor)) {
                    return true;
                }
            }
            v.unvist();
        }

        return false;
    }

    public Vertex findVertex(VertexData vertexData)
    {
        return dfs(vertexData, vertexes.elementAt(0));
    }

    private Vertex dfs(VertexData vertexData, Vertex v)
    {
        if(v.vertexData.equals(vertexData))
        {
            return v;
        }
        else
        {
            Vector<Vertex> neighbors = getNeighbors(v);
            for(Vertex neighbor : neighbors)
            {
                Vertex result = dfs(vertexData, neighbor);
                if( result != null)
                {
                    return result;
                }
            }
        }
        return null;
    }

    public Vector<Vertex> getNeighbors(Vertex v)
    {
        Vector<Vertex> neighbors = new Vector<>();
        for(Edge edge : edges)
        {
            if(edge.v.equals(v) || edge.v == v)
            {
                neighbors.add(edge.m);
            }
        }
        return neighbors;
    }

    public int[][] getAdjMatrix() {
        int[][] adjMatrix = new int[vertexes.size()][vertexes.size()];
        // initialization of the adjMatrix
        for (int i = 0; i < vertexes.size(); i++) {
            for (int j = 0; j < vertexes.size(); j++) {
                adjMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < vertexes.size(); i++) {
            Vertex v = vertexes.elementAt(i);
            for (int j = 0; j < edges.size(); j++) {
                Edge edge = edges.elementAt(j);
                if (v.equals(edge.v) || v == edge.v) {
                    adjMatrix[i][vertexes.indexOf(edge.m)] = 1;
                }
            }
        }
        return adjMatrix;
    }

    public void printAdjMatrix() {
        int[][] adjMatrix = getAdjMatrix();
        int max = vertexes.elementAt(0).vertexData.toString().length();
        for(Vertex v : vertexes)
        {
            if(v.vertexData.toString().length() > max)
            {
                max = v.vertexData.toString().length();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < adjMatrix.length; i++) {
            sb.append(String.format("%-" + max + "s [ ",vertexes.elementAt(i).vertexData));
            for (int j = 0; j < adjMatrix.length; j++) {
                sb.append(adjMatrix[i][j]).append(" ");
            }
            sb.append("]\n");
        }

        LOG.info(sb.toString());
    }
}
