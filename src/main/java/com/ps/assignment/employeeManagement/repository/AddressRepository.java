package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.assignment.employeeManagement.model.Address;
import com.ps.assignment.employeeManagement.model.Coordinates;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
    
    public Optional<Address> findByAddress(String address);

    public Optional<Address> findByCoordinates(Coordinates coordinates);

}
