package com.kpi.mst.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MST {

    private ArrayList<Edge> edges;
    private int delta;
    private int targetFunction;

    public MST() {
    }

    public MST(ArrayList<Edge> edges) {
        this.edges = edges;
        this.delta = 0;
        this.targetFunction = getEdges().stream().mapToInt(edge -> edge.weight).sum();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(List<Integer[][]> matrixes, List<MST> msts) {
        for (int i = 0; i < msts.size(); i++) {
            delta += Utility.getPurposeFunction(this, matrixes.get(i)) - msts.get(i).targetFunction;
            System.out.println(delta);
        }
    }

    public int getTargetFunction() {
        return targetFunction;
    }

    public void setTargetFunction(int targetFunction) {
        this.targetFunction = targetFunction;
    }

    @Override
    public String toString() {
        return "MST{" +
                ", delta=" + delta +
                '}';
    }

    public void printGraph(ArrayList<Edge> edgeList) {
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            System.out.println("Edge-" + i + " source: " + edge.source +
                    " destination: " + edge.destination +
                    " weight: " + edge.weight);
        }
    }

    public void updateWeight(Integer[][] matrix) {
        getEdges().forEach( edge ->{
            edge.weight = matrix[edge.destination][edge.source];
        });
    }
}
