package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.Library;
import com.example.demo.models.Location;
import com.example.demo.repositories.LibraryRepository;
import com.example.demo.repositories.LocationRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
public class LibraryRestController{

    //private Logger log = LoggerFactory.getLogger(LibraryRestController.class);

    @Autowired
    LibraryRepository libraryRepo;

    @Autowired
    LocationRepository locationRepo;

    //get Library opened between start and end dates sort alphabetically by name
    //return 404 if none exist
    @RequestMapping(value="/libraries", method = RequestMethod.GET, params={"startDate", "endDate"}, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Library>> getLibrariesByDate(@RequestParam @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern="MM-dd-yyyy") LocalDate endDate){
        
        List<Library> resultSet = libraryRepo.getLibrariesByDate(startDate, endDate);

        ResponseEntity<List<Library>> response;

        if(resultSet.size() == 0){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            response = new ResponseEntity<>(resultSet, HttpStatus.OK);

        return response;
    }

    //get Library opened at certain location defined by lat and long sort alphabetically by name
    //return 404 if none exist
    @RequestMapping(value="/libraries", method = RequestMethod.GET, params={"lat", "long"}, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Library>> getLibraryByLocation(@RequestParam("lat") Float latitude, @RequestParam("long") Float longitude){

        List<Library> resultSet = libraryRepo.getLibrariesByLocation(latitude, longitude);

        ResponseEntity<List<Library>> response;

        if(resultSet.size() == 0){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            response = new ResponseEntity<>(resultSet, HttpStatus.OK);

        return response;
    }

    //get all libraries
    @GetMapping(value="/libraries", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Library>> getAllLibraries(){
        ResponseEntity<List<Library>> response = new ResponseEntity<>(libraryRepo.findAll(), HttpStatus.OK);
        return response;
    }

     //delete all libraries
     @DeleteMapping(value="/erase")
     @ResponseStatus(HttpStatus.OK)
     public void deleteAllLibraries(){
        libraryRepo.deleteAll();
     }

    // //delete all libraries opened after startDate before endDate in a location defined by lat/long
    // @DeleteMapping(value="/erase")
    // @ResponseStatus(HttpStatus.OK)
    // public void deleteSomeLibraries(){

    // }

    //post library
    //return 409 if library exist at location
    @RequestMapping(value="/libraries", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addLibrary(@RequestBody final Library library, UriComponentsBuilder builder){
 
        Optional<Location> loc = locationRepo.findByLatAndLong(library.getLocation().getLatitude(), library.getLocation().getLongitude());
 
        if(loc.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        final Library libSaved = libraryRepo.save(library);
        
        UriComponents uriComponents = builder.path("/libraries/{id}").buildAndExpand(libSaved.getLibraryId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}