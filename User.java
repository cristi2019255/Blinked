package com.example.blinked;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String ID;
    private String Name;
    private String Phone;
    private String Group;
    private String BirthDate;
    private String ImageURL;
    private String City;
    private String Email;
    private int Popularity;
    private String CodCarnet;
    private String LastDonation;

    public String getLastDonation() {
        return LastDonation;
    }

    public void setLastDonation(String lastDonation) {
        LastDonation = lastDonation;
    }

    public User(String ID, String name, String phone, String group, String birthDate, String imageURL, String city, String email, int popularity, String codCarnet, String lastDonation) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Group = group;
        BirthDate = birthDate;
        ImageURL = imageURL;
        City = city;
        Email = email;
        Popularity = popularity;
        CodCarnet = codCarnet;
        LastDonation = lastDonation;
    }

    public String getCodCarnet() {
        return CodCarnet;
    }

    public void setCodCarnet(String codCarnet) {
        CodCarnet = codCarnet;
    }


    public User(String ID, String name, String phone, String group, String birthDate, String imageURL, String city, String email, int popularity, String codCarnet) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Group = group;
        BirthDate = birthDate;
        ImageURL = imageURL;
        City = city;
        Email = email;
        Popularity = popularity;
        CodCarnet = codCarnet;
    }

    public int getPopularity() {
        return Popularity;
    }

    public void setPopularity(int popularity) {
        Popularity = popularity;
    }


    public User(String ID, String name, String phone, String group, String birthDate, String imageURL, String city, String email, int popularitate) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Group = group;
        BirthDate = birthDate;
        ImageURL = imageURL;
        City = city;
        Email = email;
    }

    public User(){
    }

    public User(String ID, String name, String phone, String group, String birthDate, String imageURL, String city) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Group = group;
        BirthDate = birthDate;
        ImageURL = imageURL;
        City = city;
    }

    public User(String ID, String name, String phone, String group, String birthDate, String imageURL, String city, String email) {
        this.ID = ID;
        Name = name;
        Phone = phone;
        Group = group;
        BirthDate = birthDate;
        ImageURL = imageURL;
        City = city;
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

}
