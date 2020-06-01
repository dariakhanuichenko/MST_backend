package com.kpi.mst.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainServiceTest {

    private MainService mockMainService;

    @Test
    void testFileListIsEmpty() {
        try {
            mockMainService.calculate(new LinkedList<>(),5L);
            assert false;
        } catch (RuntimeException re) {
            assert true;
        }
    }

    @Test
    void testLIsEmpty() {
        List<File> inputFiles = new LinkedList<>();
        inputFiles.add(new File("test"));
        try {
            mockMainService.calculate( inputFiles,null);
            mockMainService.calculate( inputFiles,0L);
            assert false;
        } catch (RuntimeException re) {
            assert true;
        }
    }

}
