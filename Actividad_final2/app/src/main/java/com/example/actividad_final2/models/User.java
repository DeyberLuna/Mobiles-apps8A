package com.example.actividad_final2.models;

public class User {
    private String name;
    private String email;
    private String gender;



    public User(String name, String email,String gender){
        this.name=name;
        this.email=email;
        this.gender=gender;

    }
    public User(){

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
