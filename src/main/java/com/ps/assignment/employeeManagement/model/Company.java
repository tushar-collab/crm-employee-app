package com.ps.assignment.employeeManagement.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

// @Data
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "address_id")
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public String getDepartment() {
    return department;
    }

    public void setDepartment(String department) {
    this.department = department;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getTitle() {
    return title;
    }

    public void setTitle(String title) {
    this.title = title;
    }

    public Address getAddress() {
    return address;
    }

    public void setAddress(Address address) {
    this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company() {
    }

    public Company(String department, String name, String title) {
        this.department = department;
        this.name = name;
        this.title = title;
    }

    // @Override
    // public String toString() {
    // return "Company [id=" + id + ", department=" + department + ", name=" + name
    // + ", title=" + title + ", address="
    // + address + "]";
    // }

    

}
