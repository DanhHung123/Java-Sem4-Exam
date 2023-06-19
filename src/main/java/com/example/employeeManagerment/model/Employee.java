package com.example.employeeManagerment.model;

public class Employee {
    private int id;
    private String fullName;
    private String birthDay;
    private String address;
    private String position;
    private String department;

    public Employee(int id, String fullName, String birthDay, String address, String position, String department) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.address = address;
        this.position = position;
        this.department = department;
    }
    public Employee( String fullName, String birthDay, String address, String position, String department) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.address = address;
        this.position = position;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
