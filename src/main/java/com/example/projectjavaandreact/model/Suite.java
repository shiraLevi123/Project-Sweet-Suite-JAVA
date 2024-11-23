package com.example.projectjavaandreact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.awt.*;
import java.util.List;

@Entity
public class Suite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String address;
    private boolean pool;
    private boolean jacuzzi;
    private boolean wifi;
    private boolean seaView; // נוף לים
    private boolean parking; // חניה
    private boolean airConditioning; // מיזוג אוויר
    private boolean kitchenFacilities;
    private boolean tv;
    private double rating; // דירוג
    private double pricePerNight; // מחיר לפי לילה
    private int numberBeds;

    @OneToMany(mappedBy = "suite")
    private List<Diary> diaries;

    @OneToMany(mappedBy = "suite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;


    @OneToMany(mappedBy = "suite", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Images> imageList;




    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isSeaView() {
        return seaView;
    }

    public void setSeaView(boolean seaView) {
        this.seaView = seaView;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isKitchenFacilities() {
        return kitchenFacilities;
    }

    public void setKitchenFacilities(boolean kitchenFacilities) {
        this.kitchenFacilities = kitchenFacilities;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
    }

    public void setImageList(List<Images> imageList) {
        this.imageList = imageList;
    }
}