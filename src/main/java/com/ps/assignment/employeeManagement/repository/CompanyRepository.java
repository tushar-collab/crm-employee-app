package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.assignment.employeeManagement.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    public Optional<Company> findByName(String name);
    
}
