package com.ps.assignment.employeeManagement.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// @Data
@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable{

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coordinates [id=" + id + ", lat=" + lat + ", lng=" + lng + "]";
    }

    public Coordinates() {
    }

    public Coordinates(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    

}
