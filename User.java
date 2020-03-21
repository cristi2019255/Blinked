package com.example.blinked;

public class User {
    private String name;
    private String phone;
    private String email;
    private String pass;
    public User(){
    }

    public User(String name, String phone, String email, String pass) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
