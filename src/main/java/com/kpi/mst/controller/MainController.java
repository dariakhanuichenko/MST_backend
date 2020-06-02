package com.kpi.mst.controller;

import com.kpi.mst.domain.MST;
import com.kpi.mst.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping(value = "/upload/{l}", headers = "content-type=multipart/form-data")
    public ResponseEntity<List<MST>> solveFromFile(@RequestParam MultipartFile[] file,
                                             @PathVariable String l) {
        try {
            if (file != null && file.length != 0) {
                List<MST> mst = new ArrayList<>();
                System.out.println("L" + l);
                System.out.println("L" + file);
                if (l != null) {
                    List<Long> arrL = new ArrayList<>();
                    for (String str : l.split(", ")) {
                        arrL.add(Long.parseLong(str));
                    }

                    try {
                        List<File> inputFiles = new LinkedList<>();
                        System.out.println("length: " + file.length);
                        for (int i = 0; i < file.length; i++) {
                            inputFiles.add(new File(System.getProperty("java.io.tmpdir") + "/" + file[i].getOriginalFilename()));
                            file[i].transferTo(inputFiles.get(i));
                        }
                        List<Integer[][]> matrixes = mainService.readMatrixFromFile(inputFiles);
                        if (arrL.size() != matrixes.size()) {
                            throw new RuntimeException("Invalid number of l");
                        }
                        mst = mainService.calculate(matrixes, arrL);
//                inputFiles.forEach(File::deleteOnExit);
                    } catch (IOException e) {
                    }
                    return ResponseEntity.ok().body(mst);
                } else throw new RuntimeException("Files is null");
            } else throw new RuntimeException("l is null");
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping(value = "/upload-random")
    public ResponseEntity<List<MST>> solveRandom(@RequestParam("l") String l,
                                           @RequestParam("size") Integer size,
                                           @RequestParam("number") Integer number) {

//        try {
            System.out.println("L=" + l);
            if (l != null) {
                List<Long> arrL = new ArrayList<>();
                for (String str : l.split(", ")) {
                    arrL.add(Long.parseLong(str));
                }
                if (arrL.size() != number) {
                    throw new RuntimeException("Invalid number of l");
                }
                List<Integer[][]> matrixes = mainService.createRandomMatrix(size, number);
                List<MST> mst = mainService.calculate(matrixes, arrL);

                return ResponseEntity.ok().body(mst);
            } else
                throw new RuntimeException("l is null");
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.badRequest().body(null);
//        }
    }
}
