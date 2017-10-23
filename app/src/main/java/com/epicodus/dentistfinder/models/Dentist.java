package com.epicodus.dentistfinder.models;

import java.util.ArrayList;

public class Dentist {
    private String name;
    private String website;
    private String imageUrl;
    private ArrayList<String> phone = new ArrayList<>();
    private String street;
    private String city;
    private String state;
    private String zip;

    public Dentist(String name, String website, String imageUrl, ArrayList<String> phone, String street, String city, String state, String zip) {
        this.name = name;
        this.website = website;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        if (website == null){
            website = "unavailable";
        }
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}