/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.entity;

/**
 *
 * @author ASUS
 */
public class Employees {
    private String id;
    private String name;
    private String phone;
    private String gender;
    private String dayOfBirth;
    private String address;
    private String startWorkingDay;
    private String position;
    private Double salary;
    private byte image; 

    public Employees() {
    }

    public Employees(String id, String name, String phone, String gender, String dayOfBirth, String address, String startWorkingDay, String position, Double salary, byte image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.startWorkingDay = startWorkingDay;
        this.position = position;
        this.salary = salary;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartWorkingDay() {
        return startWorkingDay;
    }

    public void setStartWorkingDay(String startWorkingDay) {
        this.startWorkingDay = startWorkingDay;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public byte getImage() {
        return image;
    }

    public void setImage(byte image) {
        this.image = image;
    }

    
    
}
