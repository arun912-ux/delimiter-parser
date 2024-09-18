package com.example.mine.delimiterseparatedfileparser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    void shouldTestParseFileAndChangeDelimiter() throws IOException {

        MultipartFile multipartFile = new MockMultipartFile("test.csv", "test.csv", "text/csv", "test,test,test".getBytes());
        File file = fileService.parseFileAndChangeDelimiter(multipartFile, ",", ";");
        assertNotNull(file);

    }

}