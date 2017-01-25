package com.example.qasimnawaz.campusrecruitmentsystem.Modeules;

/**
 * Created by Qasim Nawaz on 1/19/2017.
 */

public class StudentModule {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String contactNo;
    private String address;
    private String fscPercent;
    private String fscYear;
    private String sscPercent;
    private String sscYear;
    private String university;
    private String department;
    private String password;

    public StudentModule() {
    }

    public StudentModule(String uuid, String firstName, String lastName, String gender, String email, String contactNo, String address, String fscPercent, String fscYear, String sscPercent, String sscYear, String university, String department) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.fscPercent = fscPercent;
        this.fscYear = fscYear;
        this.sscPercent = sscPercent;
        this.sscYear = sscYear;
        this.university = university;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFscPercent() {
        return fscPercent;
    }

    public void setFscPercent(String fscPercent) {
        this.fscPercent = fscPercent;
    }

    public String getFscYear() {
        return fscYear;
    }

    public void setFscYear(String fscYear) {
        this.fscYear = fscYear;
    }

    public String getSscPercent() {
        return sscPercent;
    }

    public void setSscPercent(String sscPercent) {
        this.sscPercent = sscPercent;
    }

    public String getSscYear() {
        return sscYear;
    }

    public void setSscYear(String sscYear) {
        this.sscYear = sscYear;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
