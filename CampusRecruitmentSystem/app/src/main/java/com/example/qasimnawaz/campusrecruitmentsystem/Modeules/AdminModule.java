package com.example.qasimnawaz.campusrecruitmentsystem.Modeules;

/**
 * Created by Qasim Nawaz on 1/22/2017.
 */

public class AdminModule {
    private String name;
    private String position;
    private String email;

    public AdminModule(String name, String position, String email) {
        this.name = name;
        this.position = position;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdminModule(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
