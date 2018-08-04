package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.models.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>{

    @Query("SELECT lib from Library lib WHERE lib.openDate between :startDate and :endDate order by lib.name")
    List<Library> getLibrariesByDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT lib From Library lib join fetch lib.location loc where loc.latitude = :latitude and loc.longitude = :longitude order by lib.name")
    List<Library> getLibrariesByLocation(Float latitude, Float longitude);

    @Query("")
    List<Library> getLibrariesByParams(LocalDate startDate, LocalDate endDate, Float latitude, Float longitude);

    @Override
    @Query("Select lib from Library lib order by name")
    List<Library> findAll();

}