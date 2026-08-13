package org.demo.mavenbuildanalyzer.controller;

import org.demo.mavenbuildanalyzer.model.BuildAnalysis;
import org.demo.mavenbuildanalyzer.service.BuildAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/builds")
public class BuildAnalysisController {

    private final BuildAnalysisService buildAnalysisService;

    public BuildAnalysisController(BuildAnalysisService buildAnalysisService) {
        this.buildAnalysisService = buildAnalysisService;
    }

    @PostMapping("/analyze")
    public BuildAnalysis analyze(@RequestParam("file")MultipartFile file)
            throws IOException {
        if(file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        String logContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        return buildAnalysisService.analyze(logContent);
    }
}
