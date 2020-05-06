package com.kpi.mst.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {
    private int vertices;
    private ArrayList<Edge> allEdges = new ArrayList<>();

    Graph(int vertices) {
        this.vertices = vertices;
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge> getAllEdges() {
        return allEdges;
    }

    public void setAllEdges(ArrayList<Edge> allEdges) {
        this.allEdges = allEdges;
    }

// добавить ребро

    public void addEgde(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge); //add to total edges
    }
 /// алгорим краскала
    public MST kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

        //add all the edges to priority queue, //sort the edges on weights
        pq.addAll(allEdges);

        //create a parent []
        int[] parent = new int[vertices];

        //makeset
        makeSet(parent);

        ArrayList<Edge> mst = new ArrayList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < vertices - 1) {
            Edge edge = pq.remove();
            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                mst.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
        }
        //print MST
        System.out.println("Minimum Spanning Tree: ");
        printGraph(mst);
        return new MST(mst);
    }

    public void makeSet(int[] parent) {
        //Make set- creating a new element with a parent pointer to itself.
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    public int find(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        if (parent[vertex] != vertex)
            return find(parent, parent[vertex]);
        ;
        return vertex;
    }

    public void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    public void printGraph(ArrayList<Edge> edgeList) {
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            System.out.println("Edge-" + i + " source: " + edge.source +
                    " destination: " + edge.destination +
                    " weight: " + edge.weight);
        }
    }


}
