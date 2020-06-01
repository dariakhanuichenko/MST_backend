package com.kpi.mst.service;

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
public class MainService {

    private final Logger log = LoggerFactory.getLogger(MainService.class);

    private final DataReader reader;

    public MainService(DataReader reader) {
        super();
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

    public MST calculate(List<Integer[][]> matrixes, List<Long> l) {

        List<MST> msts = new LinkedList<>();


        if (l == null || l.size() == 0) {
            throw new RuntimeException("l  is null or l = 0");
        }

        int vertices = matrixes.get(0)[0].length;
        long m = System.currentTimeMillis();

        additionalMatrix(matrixes, vertices);

        // FIRST STEP
        // создали МКД для каждой матрицы
        System.out.println("FIRST");
        matrixes.forEach(matrix -> msts.add(Utility.firstStep(matrix, vertices)));
        msts.get(vertices).setAdditional(true);

        // SECOND STEP
        // для каждого МКД посчитать дельту
        System.out.println("SECOND");
        msts.forEach(mst -> {
            mst.setDelta(matrixes, msts);
        });
        setDelta(matrixes,msts); // for additional

        System.out.println();
        // THIRD STEP
        System.out.println("THIRD");
        int index = Utility.thirdStep(Mapper.mapListMSTToListDelta(msts), l);
        System.out.println();
        msts.get(index).updateWeight(matrixes.get(index));
        msts.get(index).setTargetFunction();
        System.out.println("Time: " + (double) (System.currentTimeMillis() - m));
        return msts.get(index);
    }

    public void setDelta(List<Integer[][]> matrixes, List<MST> msts) {
        int delta = 0;
        System.out.println("DELTA: ");
        for (int i = 0; i < msts.size()-1; i++) {
            System.out.print((i+1)+". ");
            delta += Utility.getPurposeFunction(msts.get(msts.size()-1), matrixes.get(i)) - msts.get(msts.size()-1).getTargetFunction();

            System.out.println("delta = " +Utility.getPurposeFunction( msts.get(msts.size()-1), matrixes.get(i)) + " - "+ msts.get(i).getTargetFunction() +" = " +(Utility.getPurposeFunction(msts.get(msts.size()-1), matrixes.get(i)) - msts.get(i).getTargetFunction()));
        }
        msts.get(msts.size()-1).setDelta(delta);
        System.out.println("result delta : " + delta);
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
                matrixNew[i][j]/=size;
                System.out.print(matrixNew[i][j] + " ");
                if (j == size - 1)
                    System.out.println();
            }
        }
        matrixes.add(matrixNew);
    }
}
