package com.example.Recon.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
    @Id
    private Integer id;
    private String nameInFile1;
    private String nameInFile2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameInFile1() {
        return nameInFile1;
    }

    public void setNameInFile1(String nameInFile1) {
        this.nameInFile1 = nameInFile1;
    }

    public String getNameInFile2() {
        return nameInFile2;
    }

    public void setNameInFile2(String nameInFile2) {
        this.nameInFile2 = nameInFile2;
    }
}