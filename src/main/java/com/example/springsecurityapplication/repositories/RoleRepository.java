package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
