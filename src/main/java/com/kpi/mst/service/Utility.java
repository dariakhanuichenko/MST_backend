package com.kpi.mst.service;

import com.kpi.mst.domain.Graph;
import com.kpi.mst.domain.MST;

import java.util.*;
import java.util.stream.Collectors;

public class Utility {

    public static Graph initial(Integer[][] matrix, int vertices) {

        if (vertices <= 0) {
            throw new RuntimeException("Invalid number of vertices");
        }
        Graph graph = new Graph(vertices);

        for (int i = 0; i < vertices; i++) {
            for (int j = i; j < vertices; j++) {
                if (matrix[i][j] != 0) {

                    graph.addEgde(i, j, matrix[i][j]);
                }
            }
        }
        return graph;
    }

    // ЦФ

    public static int getPurposeFunction(MST graph) {
        return graph.getEdges().stream().mapToInt(edge -> edge.weight).sum();
    }

    public static int getPurposeFunction(MST graph, Integer[][] matrix) {
        graph.getEdges().forEach(edge -> {
            edge.weight = matrix[edge.destination][edge.source];
        });
        return getPurposeFunction(graph);
    }


    //===========================
    //FIRST STEP
    public static MST firstStep(Integer[][] matrix, int vertices) {
        Graph graph1 = Utility.initial(matrix, vertices);
        return graph1.kruskalMST();
    }

    public static List<Integer> thirdStep(List<Integer> deltas) {
        Map<Integer, Integer> indexes = new HashMap<>();
        Integer minDelta = deltas.stream().min((Integer::compare)).get();

        for (int i = 0; i < deltas.size(); i++) {
            if (deltas.get(i).equals(minDelta)) {
                indexes.put(i, deltas.get(i));
            }
        }

//        if(indexes.containsValue( deltas.get(deltas.size()-1)) || indexes.entrySet().stream().filter(entry -> entry.getValue()>=deltas.get(deltas.size()-1)).collect(Collectors.toList()).size()>0)
//        indexes.put(deltas.size()-1, deltas.get(deltas.size()-1));
//        if (indexes.entrySet().size() > 1) {
//            return min(indexes);
//        } else {
            return new ArrayList<>(indexes.keySet());
//        }
    }

    public static <Integer, V extends Comparable<V>> Integer min(Map<Integer, V> map) {
        Optional<Map.Entry<Integer, V>> maxEntry = map.entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry::getValue)
                );

        return maxEntry.get().getKey();
    }


}
