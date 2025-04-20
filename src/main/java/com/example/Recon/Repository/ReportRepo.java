package com.example.Recon.Repository;

import com.example.Recon.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends CrudRepository<Report, Integer>{
}