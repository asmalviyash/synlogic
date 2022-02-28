package com.synalogikrest.synalogikrest.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FileUploadController {

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty");

        }
        if (!file.getContentType().equals("text/plain")) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only text content is allowed");

        }

        //File process code
        ObjectMapper mapper = new ObjectMapper();
        String fileContent = new BufferedReader(new InputStreamReader(file.getInputStream()))
                .lines().parallel().collect(Collectors.joining("\n"));

        List<Integer> b = Arrays.asList(fileContent.split(" ")).stream().map(p -> p.length()).collect(Collectors.toList());

        Map<Integer, Long> counters = b.stream()
                .collect(Collectors.groupingBy(l -> l,
                        Collectors.counting()));

        Long max = counters.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        String mostOccrenace = counters
                .entrySet()
                .stream()
                .filter(entry -> max.equals(entry.getValue()))
                .map(entry -> entry.getKey().toString())
                .collect(Collectors.joining(", "));

        IntSummaryStatistics wordStatistics = Pattern.compile(" ").splitAsStream(fileContent)
                .mapToInt(word -> word.length())
                .summaryStatistics();

        DecimalFormat df = new DecimalFormat("0.000");

        List<String> fileResultList = new ArrayList<>();
        fileResultList.add("Word count " + wordStatistics.getCount()); //word count
        fileResultList.add("Average word length " + df.format(wordStatistics.getAverage()));//avr word length
        counters.forEach((k, v) -> fileResultList.add(String.format("Number of words of length %d is %d", k, v)));
        fileResultList.add(String.format("The most frequently occuring word length is %d for word lengths of %s", max, mostOccrenace));

        return ResponseEntity.ok(mapper.writeValueAsString(fileResultList));

    }
}
