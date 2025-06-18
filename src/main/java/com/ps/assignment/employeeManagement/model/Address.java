package com.ps.assignment.employeeManagement.model;

import java.io.Serializable;

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

@Entity
@Table(name = "address")
public class Address implements Serializable{

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "fk_coordinates_id" , referencedColumnName = "id")
    private Coordinates coordinates;
    
    @Column(name = "country")
    private String country;

    public Address() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address(String address, String city, String state, String stateCode, String postalCode, String country) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.country = country;
    }


    @Override
    public String toString() {
        return "Address [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", stateCode="
                + stateCode + ", postalCode=" + postalCode + ", coordinates=" + coordinates + ", country=" + country
                + "]";
    }

}
