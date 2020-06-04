package com.kpi.mst.service.mapper;

import com.kpi.mst.domain.MST;
import com.kpi.mst.domain.SimpleStatistics;
import com.kpi.mst.domain.Statistics;
import com.kpi.mst.domain.StatisticsDTO;
import com.kpi.mst.service.MainService;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<Integer> mapListMSTToListDelta( List<MST> msts) {
        List<Integer> deltas = new ArrayList<>();
        msts.forEach( mst -> deltas.add(mst.getDelta()));
        System.out.println("Result deltas: ");
        System.out.println(deltas.toString());
        return deltas;
    }

    public static StatisticsDTO toDto(Statistics statistic){
        return new StatisticsDTO(statistic.getTime(), statistic.getNumberMatrix(), statistic.getSizeMatrix(), statistic.getDiaphazone());
    }

    public static List<StatisticsDTO> listToDto(List<Statistics> statistics) {
        List<StatisticsDTO> dtos = new ArrayList<>();
        statistics.forEach( s -> dtos.add(toDto(s)));
        return dtos;
    }
    public static StatisticsDTO simpleToDto(SimpleStatistics statistic){
        return new StatisticsDTO(statistic.getTime(), statistic.getNumberMatrix(), statistic.getSizeMatrix(), statistic.getDiaphazone());
    }

    public static  List<StatisticsDTO> simpleListToDto(List<SimpleStatistics> statistics) {
        List<StatisticsDTO> dtos = new ArrayList<>();
        statistics.forEach( s -> dtos.add(simpleToDto(s)));
        return dtos;
    }
}
