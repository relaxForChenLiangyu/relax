package com.example.cynthia.relax.beans;


public class User {
    String phone;
    String password;

    public User(){

    }

    public User(String phone,String password){
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
