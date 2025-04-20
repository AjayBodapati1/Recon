package com.example.Recon.service;

import com.example.Recon.Repository.File1Repo;
import com.example.Recon.Repository.File2Repo;
import com.example.Recon.Repository.ReportRepo;
import com.example.Recon.entity.File1;
import com.example.Recon.entity.File2;
import com.example.Recon.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class CsvService {

    @Autowired
    private File1Repo file1Repo;

    @Autowired
    private File2Repo file2Repo;

    @Autowired
    private ReportRepo reportRepo;

    public  void processCsvFiles(MultipartFile file1, MultipartFile file2) throws IOException {
        Map<Integer, String> map1 = parseAndSaveFile(file1, file1Repo, File1.class);
        Map<Integer, String> map2 = parseAndSaveFile(file2, file2Repo, File2.class);

        // Compare and save mismatches
        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            Integer id = entry.getKey();
            String name1 = entry.getValue();
            String name2 = map2.get(id);

            if (name2 != null && !name1.equals(name2)) {
                Report report = new Report();
                report.setId(id);
                report.setNameInFile1(name1);
                report.setNameInFile2(name2);
                reportRepo.save(report);
            }
        }
    }

    private Map<Integer, String> parseAndSaveFile(MultipartFile file, CrudRepository<?, Integer> repo, Class<?> entityClass) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        Map<Integer, String> dataMap = new HashMap<>();

        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue; // Skip header
            }
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            dataMap.put(id, name);

            if (entityClass == File1.class) {
                file1Repo.save(new File1(id, name));
            } else if (entityClass == File2.class) {
                file2Repo.save(new File2(id, name));
            }
        }
        return dataMap;
    }
}