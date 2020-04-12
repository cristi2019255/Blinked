package com.example.blinked;

public class Alert {
    private String ID;
    private String Email;
    private String ID_placer;

    public String getID_placer() {
        return ID_placer;
    }

    public void setID_placer(String ID_placer) {
        this.ID_placer = ID_placer;
    }

    public Alert(String ID, String email, String ID_placer, String years, String city, String group, String phone, String details, String date) {
        this.ID = ID;
        Email = email;
        this.ID_placer = ID_placer;
        this.years = years;
        this.city = city;
        this.group = group;
        this.phone = phone;
        Details = details;
        Date = date;
    }

    private String years,city,group,phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Alert(String ID, String email, String years, String city, String group, String phone, String details, String date) {
        this.ID = ID;
        Email = email;
        this.years = years;
        this.city = city;
        this.group = group;
        this.phone = phone;
        Details = details;
        Date = date;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Alert(String ID, String email, String years, String city, String group, String details, String date) {
        this.ID = ID;
        Email = email;
        this.years = years;
        this.city = city;
        this.group = group;
        Details = details;
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String Details;

    public Alert(String ID, String email, String details, String date) {
        this.ID = ID;
        Email = email;
        Details = details;
        Date = date;
    }

    private String Date;

    public Alert(){

    }

    public Alert(String ID, String email, String details) {
        this.ID = ID;
        Email = email;
        Details = details;
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

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
