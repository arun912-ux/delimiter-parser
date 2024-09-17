package com.example.mine.delimiterseparatedfileparser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    void shouldTestParseFileAndChangeDelimiter() {

        assertThrows(NullPointerException.class, () -> fileService.parseFileAndChangeDelimiter(null, null, null));

    }

}