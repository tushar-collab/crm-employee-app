package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // Define any custom query methods if needed
    // For example:
    // List<Department> findByName(String name);
    
    // You can also use Spring Data JPA's derived query methods
    // or @Query annotations for more complex queries.
    
}
