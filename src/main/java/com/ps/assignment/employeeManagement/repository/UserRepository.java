package com.ps.assignment.employeeManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps.assignment.employeeManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public Optional<User> findByEmail(String email);

    @Query("SELECT u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone, u.company.department, u.age, u.ssn FROM User u")
    public List<Object[]> fetchUsers();

    @Query("SELECT u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone, u.company.department, u.age, u.ssn FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :fName, '%'))")
    public Optional<List<Object[]>> findUsersByFirstName(@Param("fName") String firstName);

    @Query("SELECT u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone, u.company.department, u.age, u.ssn FROM User u WHERE LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lName, '%'))")
    public Optional<List<Object[]>> findUsersByLastName(@Param("lName") String lastName);

    @Query("SELECT u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone, u.company.department, u.age, u.ssn FROM User u WHERE LOWER(u.ssn) LIKE LOWER(CONCAT('%', :ssn, '%'))")
    public Optional<List<Object[]>> findUsersBySsn(@Param("ssn") String ssn);

    public Optional<User> findById(Integer id);
}
