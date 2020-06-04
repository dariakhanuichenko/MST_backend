package com.kpi.mst.domain;

import com.kpi.mst.service.Utility;

import java.util.ArrayList;
import java.util.List;

public class MST {

    private ArrayList<Edge> edges;
    private int delta;
    private int targetFunction;
    private boolean isAdditional;

    public MST() {
    }

    public MST(ArrayList<Edge> edges) {
        this.isAdditional = false;
        this.edges = edges;
        this.delta = 0;
        this.targetFunction = getEdges().stream().mapToInt(edge -> edge.weight).sum();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getDelta() {
        return delta;
    }

    public boolean isAdditional() {
        return isAdditional;
    }

    public void setAdditional(boolean additional) {
        isAdditional = additional;
    }

    public int getTargetFunction() {
        return targetFunction;
    }

    public void setDelta(List<Integer[][]> matrixes, List<MST> msts) {
        System.out.println("DELTA: ");
        for (int i = 0; i < msts.size(); i++) {
            if (i == msts.size()-1)
                continue;
            System.out.print((i+1)+". ");

            delta += Utility.getPurposeFunction(this, matrixes.get(i)) - msts.get(i).targetFunction;

            System.out.println("delta = " +Utility.getPurposeFunction(this, matrixes.get(i)) + " - "+ msts.get(i).targetFunction +" = " +(Utility.getPurposeFunction(this, matrixes.get(i)) - msts.get(i).targetFunction));
        }

        System.out.println("result delta : " + delta);
    }

    public void setDeltaAdditional(List<Integer[][]> matrixes, List<MST> msts) {
        System.out.println("DELTA: ");
        for (int i = 0; i < msts.size(); i++) {
            System.out.print((i+1)+". ");

            delta += Utility.getPurposeFunction(this, matrixes.get(i)) - msts.get(i).targetFunction;

            System.out.println("delta = " +Utility.getPurposeFunction(this, matrixes.get(i)) + " - "+ msts.get(i).targetFunction +" = " +(Utility.getPurposeFunction(this, matrixes.get(i)) - msts.get(i).targetFunction));
        }

        System.out.println("result delta : " + delta);
    }

    public void setTargetFunction(){
        this.targetFunction = getEdges().stream().mapToInt(edge -> edge.weight).sum();
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
    @Override
    public String toString() {
        return "MST{" +
                ", delta=" + delta +
                '}';
    }

    public void updateWeight(Integer[][] matrix) {
        getEdges().forEach( edge ->{
            edge.weight = matrix[edge.destination][edge.source];
        });
    }
}
