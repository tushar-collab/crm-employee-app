package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.assignment.employeeManagement.model.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
    
    public Optional<Coordinates> findByLatAndLng(String lat, String lng);

    public Optional<Coordinates> findById(Integer id);

}
