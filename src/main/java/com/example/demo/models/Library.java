package com.example.demo.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.example.demo.models.Location;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Library {

    @Id
    @GeneratedValue
    private Long library_id;

    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    @DateTimeFormat(pattern="MM-dd-yyyy")
    private LocalDate openDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Location location;

    public Library(){
        super();
    }

    public Library (String name, LocalDate openDate, Location location){
        super();
        this.name = name;
        this.openDate = openDate;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }    

    public LocalDate getOpenDate(){
        return openDate;
    }

    public void setOpenDate(LocalDate openDate){
        this.openDate = openDate;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Long getLibraryId(){
        return library_id;
    }

}