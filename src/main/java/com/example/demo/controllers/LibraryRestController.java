package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.repositories.LibraryRepository;
import com.example.demo.models.Library;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
public class LibraryRestController{

    @Autowired
    LibraryRepository libraryRepo;

    //get all libraries
    //return 404 if none exist
    @GetMapping(value="/libraries", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> getLibraries(){
        return new ArrayList<Library>();
    }

    //get Library opened between start and end dates sort alphabetically by name
    //return 404 if none exist
    @GetMapping(value="/libraries", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> getLibrariesByDate(@RequestParam Date startDate, @RequestParam Date endDate){
        return new ArrayList<Library>();
    }

    //get Library opened at certain location defined by lat and long sort alphabetically by name
    //return 404 if none exist
    @GetMapping(value="/libraries", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Library> getLibraryByLocation(@RequestParam("lat") Float latitude, @RequestParam("long") Float longitude){
        return new ArrayList<Library>();
    }

     //delete all libraries
     @DeleteMapping(value="/libraries")
     @ResponseStatus(HttpStatus.OK)
     public void deleteLibrary(){
 
     }

    //delete all libraries opened after startDate before endDate in a location defined by lat/long
    @DeleteMapping(value="/libraries")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSomeLibraries(){

    }

    //post library
    //return 403 if library exist at location
    @PostMapping(value="/libraries", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLibrary(){

    }


}