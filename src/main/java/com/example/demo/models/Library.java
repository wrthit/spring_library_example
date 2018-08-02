package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.example.demo.models.Location;

@Entity
public class Library {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(nullable = false)
    private Date openDate;

    @OneToOne
    @Column(nullable = false)
    private Location location;

    public Library(){
        super();
    }

    public Library (String name, Date openDate, Location location){
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

    public Date getOpenDate(){
        return openDate;
    }

    public void setOpenDate(Date openDate){
        this.openDate = openDate;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}