package com.kpi.mst.controller;

import com.kpi.mst.service.MST;
import com.kpi.mst.service.MainService;
import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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
    public ResponseEntity<MST> handleFileUpload(@RequestParam MultipartFile[] file,
                                                @PathVariable Long l) {
        if (file != null && file.length != 0) {
            MST mst = new MST();
            System.out.println("LLLLL" + l);
            System.out.println("LLLLL" + file);
            try {
                List<File> convFiles = new LinkedList<>();
                System.out.println("length: " + file.length);
                for (int i = 0; i < file.length; i++) {
                    convFiles.add(new File(System.getProperty("java.io.tmpdir") + "/" + file[i].getOriginalFilename()));
                    file[i].transferTo(convFiles.get(i));

                }
                mst = mainService.calculate(convFiles, l);
//                convFiles.forEach(File::deleteOnExit);
            } catch (IOException e) {
            }
            return ResponseEntity.ok().body(mst);
        } else throw new RuntimeException("Files is null");

    }

    private void deleteFiles(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }
}
