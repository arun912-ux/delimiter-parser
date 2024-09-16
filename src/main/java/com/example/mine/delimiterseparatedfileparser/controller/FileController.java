package com.example.mine.delimiterseparatedfileparser.controller;

import com.example.mine.delimiterseparatedfileparser.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class FileController {


    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @GetMapping
    public String index() {
        return "Hello World";
    }


    @PostMapping("/upload")
    public ResponseEntity<Resource> upload(@RequestParam("file") MultipartFile file,
                                 @RequestParam("inputDelimiter") String inputDelimiter,
                                 @RequestParam("outputDelimiter") String outputDelimiter) throws IOException {


        log.info("FileController: delimiter: {}", inputDelimiter);
        log.info("FileController: file name: {}", file.getOriginalFilename());
        log.info("FileController: outputDelimiter: {}", outputDelimiter);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        File changed = fileService.parseFileAndChangeDelimiter(file, inputDelimiter, outputDelimiter);
        Resource resource = new FileSystemResource(changed);

        log.info("FileController: changed: {}", resource);

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + changed.getName() + "\"");
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }


}
