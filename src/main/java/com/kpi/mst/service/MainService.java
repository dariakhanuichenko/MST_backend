package com.kpi.mst.service;

import com.kpi.mst.domain.MST;
import com.kpi.mst.domain.Statistics;
import com.kpi.mst.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MainService {

    private final Logger log = LoggerFactory.getLogger(MainService.class);

    private final DataReader reader;
    private final StatisticsService service;

    public MainService(DataReader reader, StatisticsService service) {
        super();
        this.service = service;
        this.reader = reader;
    }

    public List<Integer[][]> createRandomMatrix(Integer size, Integer number) {

        List<Integer[][]> list = new LinkedList<>();
        for (int k = 0; k < number; k++) {

            Integer[][] matrix = new Integer[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = ((int) (Math.random() * 10 + 1));
                    matrix[j][i] = matrix[i][j];
                    if (i == j) {
                        matrix[i][j] = 0;
                    }
                }
            }
            list.add(matrix);
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    System.out.print(matrix[j][i] + " ");
                    if (i == size - 1) {
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }


        return list;
    }


    public List<Integer[][]> readMatrixFromFile(List<File> files) {
        List<Integer[][]> matrixes = new LinkedList<>();
        if (files.size() == 0) {
            throw new RuntimeException("File list is empty");
        }
        files.forEach(file ->
        {
            System.out.println(file);
            try {
                matrixes.add(reader.readData(file));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                throw new RuntimeException("invalid file format ");
            }
        });
        return matrixes;
    }

    public List<MST> calculate(List<Integer[][]> matrixes) {

        List<MST> msts = new LinkedList<>();

        int vertices = matrixes.get(0)[0].length;
        long m = System.currentTimeMillis();

        // ZERO STEP
        // сформировать обопщенную матрицу
        additionalMatrix(matrixes, vertices);

        // FIRST STEP
        // создали МКД для каждой матрицы
        System.out.println("FIRST");
        matrixes.forEach(matrix -> msts.add(Utility.firstStep(matrix, vertices)));
        msts.get(matrixes.size()-1).setAdditional(true);

        // SECOND STEP
        // для каждого МКД посчитать дельту
        System.out.println("SECOND");
        for(int i = 0; i<msts.size(); i++) {
             if( i == msts.size()-1) {
                 msts.get(i).setDeltaAdditional(matrixes, msts);
             } else {
                 msts.get(i).setDelta(matrixes, msts);
             }
        }

        System.out.println();
        // THIRD STEP
        System.out.println("THIRD");
        List<Integer> index = Utility.thirdStep(Mapper.mapListMSTToListDelta(msts));
        System.out.println();
        List<MST> result = new ArrayList<>();
        index.forEach( i ->{
            msts.get(i).updateWeight(matrixes.get(i));
            msts.get(i).setTargetFunction();
            result.add(msts.get(i));
        });


        boolean isAdditional = result.stream().anyMatch(MST::isAdditional);
        service.save( new Statistics((double) (System.currentTimeMillis() - m), matrixes.size()-1, vertices,isAdditional, "1-10"));
        System.out.println("Time: " + (double) (System.currentTimeMillis() - m));
        return result;
    }


    private void additionalMatrix(List<Integer[][]> matrixes, Integer size) {
        Integer[][] matrixNew = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixNew[i][j] = 0;
            }
        }
        for (Integer[][] matrix : matrixes) {

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrixNew[i][j] += matrix[i][j];
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrixNew[i][j] + " ");
                if (j == size - 1)
                    System.out.println();
            }
        }
        matrixes.add(matrixNew);
    }
}
