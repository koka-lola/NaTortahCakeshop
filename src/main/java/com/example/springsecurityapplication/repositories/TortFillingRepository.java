package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.TortFilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TortFillingRepository extends JpaRepository<TortFilling, Integer>{
    TortFilling findByName(String name);
}
