package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location{

    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;

    private String city;

    private Float latitude;

    private Float longitude;

    public Location(){
        super();
    }

    public Location(String city, Float latitude, Float longitude){
        super();
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public Float getLatitude(){
        return latitude;
    }

    public void setLatitude(Float latitude){
        this.latitude = latitude;
    }

    public Float getLongitude(){
        return longitude;
    }

    public void setLongitude(Float longitude){
        this.longitude = longitude;
    }
}