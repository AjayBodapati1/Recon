package com.example.Recon.controller;

import com.example.Recon.Repository.ReportRepo;
import com.example.Recon.entity.Report;
import com.example.Recon.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@RestController
@RequestMapping("/api/csv")
@CrossOrigin(origins = "http://localhost:4200")
public class ReconController {

    @Autowired
    private CsvService csvService;

    @Autowired
    private ReportRepo reportRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        try {
            csvService.processCsvFiles(file1, file2);
            return ResponseEntity.ok("Files uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing files: " + e.getMessage());
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = (List<Report>) reportRepository.findAll();
        return ResponseEntity.ok(reports);
    }
}
