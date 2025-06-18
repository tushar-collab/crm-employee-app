package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.assignment.employeeManagement.model.Hair;

@Repository
public interface HairRepository extends JpaRepository<Hair, Long> {
    
    public Optional<Hair> findByColorAndType(String color, String type);

}
