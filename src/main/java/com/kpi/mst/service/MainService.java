package com.kpi.mst.service;

import com.kpi.mst.controller.MainController;
import com.kpi.mst.domain.MST;
import com.kpi.mst.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class MainService  {

    private final Logger log = LoggerFactory.getLogger(MainService.class);

    private final DataReader reader;

    public MainService(DataReader reader) {
        super();
        this.reader = reader;
    }

    public MST calculate(List<File> files, Long l) {

        List<Integer[][]> matrixes = new LinkedList<>();
        List<MST> msts = new LinkedList<>();
        files.forEach(file ->
        {
            System.out.println(file);
            try {
                matrixes.add(reader.readData(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        int vertices = matrixes.get(0)[0].length;

        // FIRST STEP
        // создали МКД для каждой матрицы
        System.out.println("FIRST");
        matrixes.forEach(matrix -> msts.add(Utility.firstStep(matrix, vertices)));

        // SECOND STEP
        // для каждого МКД посчитать дельту
        System.out.println("SECOND");
        msts.forEach(mst -> {
            mst.setDelta(matrixes, msts);
        });

        // THIRD STEP
        System.out.println("THIRD");
        int index = Utility.thirdStep(Mapper.mapListMSTToListDelta(msts), l);
        System.out.println();
        msts.get(index).updateWeight(matrixes.get(index));
        return msts.get(index);
    }
}
