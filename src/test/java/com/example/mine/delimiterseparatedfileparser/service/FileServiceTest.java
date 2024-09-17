package com.example.mine.delimiterseparatedfileparser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    void shouldTestParseFileAndChangeDelimiter() throws IOException {

        fileService.parseFileAndChangeDelimiter(null, null, null);

    }

}