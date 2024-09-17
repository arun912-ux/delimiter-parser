package com.example.mine.delimiterseparatedfileparser.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
public class FileService {

    public File parseFileAndChangeDelimiter(MultipartFile multipartFile, String inputDelimiter, String outputDelimiter) throws IOException {

        InputStream fileInputStream = null;
        Reader reader = null;
        try {
            byte[] multipartFileBytes = multipartFile.getBytes();
            log.info("FileService: multipartFileBytes.length: {}", multipartFileBytes.length);
            fileInputStream = multipartFile.getInputStream();
            reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("FileService: error: {}", e.getMessage());
            throw e;
        }


        CSVFormat inFormat = CSVFormat.Builder.create()
                .setDelimiter(inputDelimiter)
                .setHeader().setSkipHeaderRecord(true)
                .build();

        CSVParser parser = new CSVParser(reader, inFormat);
        Map<String, Integer> headerMap = parser.getHeaderMap();
        log.info("FileService: headerMap: {}", headerMap);
        log.info("FileService: parser.getHeaderNames: {}", parser.getHeaderNames());


        String outputFileName = multipartFile.getOriginalFilename() + "-" + Instant.now().toEpochMilli() + "-output.csv";
        File outputFile = new File("/tmp/" + outputFileName);
        if (!outputFile.exists()) {
            boolean newFile = outputFile.createNewFile();
            log.info("FileService: file created: {} with fileName: {}", newFile, outputFileName);
        }
        log.info("FileService: outputFileName: {}", outputFile.exists());
        PrintStream printStream = new PrintStream(outputFile);


        CSVFormat outFormat = CSVFormat.Builder.create()
                .setDelimiter(outputDelimiter)
                .setHeader().setSkipHeaderRecord(true)
                .build();
        // CSVPrinter to print records to console
//        CSVPrinter csvPrinter = new CSVPrinter(System.out, CSVFormat.Builder.create().build());
        CSVPrinter csvPrinter = new CSVPrinter(printStream, outFormat);

        for (CSVRecord csvRecord : parser) {
            log.info("csvRecord: {}", csvRecord);
            csvPrinter.printRecord(csvRecord);
        }


        csvPrinter.close();
        parser.close();
        printStream.close();


        return outputFile;
    }

}
