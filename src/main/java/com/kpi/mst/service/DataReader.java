package com.kpi.mst.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class DataReader {

    public DataReader() {
    }

    // "C:\\Users\\deyad\\Desktop\\labs\\mateix.txt"
    public  Integer[][] readData( File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>();
        while (br.ready()) {
            lines.add(br.readLine());
        }
        int matrixWidth = Integer.parseInt(lines.get(0));

        Integer[][] matrix = new Integer[matrixWidth][matrixWidth];

        for (int i = 1; i < matrixWidth+1; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                String[] line = lines.get(i).split(" ");
                matrix[i-1][j] = Integer.parseInt(line[j]);
                System.out.println(matrix[i-1][j]);
            }
        }

        for (int i = 0; i < matrixWidth; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        br.close();
        return matrix;
    }


}
