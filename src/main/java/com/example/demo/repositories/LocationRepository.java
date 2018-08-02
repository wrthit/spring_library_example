package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import com.example.demo.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

    @Query("Select loc from Location loc where loc.latitude = :latitude and loc.longitude= :longitude")
    Optional<Location> findByLatAndLong(Float latitude, Float longitude);
}