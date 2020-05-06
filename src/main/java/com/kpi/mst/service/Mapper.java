package com.kpi.mst.service;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<Integer> mapListMSTToListDelta( List<MST> msts) {
        List<Integer> deltas = new ArrayList<>();
        msts.forEach( mst -> deltas.add(mst.getDelta()));
        System.out.println(deltas.toString());
        return deltas;
    }
}
