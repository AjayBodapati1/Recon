package com.example.Recon.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file2")
public class File2 {
    @Id
    private Integer id;
    private String name;

    public File2() {
    }
    public File2(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer ID){
        this.id = ID;
    }

    public String getName(){
        return name;
    }
    public void setName(String Name){
        this.name = name;
    }
}
