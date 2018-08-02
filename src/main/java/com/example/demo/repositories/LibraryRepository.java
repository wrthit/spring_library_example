package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>{

}