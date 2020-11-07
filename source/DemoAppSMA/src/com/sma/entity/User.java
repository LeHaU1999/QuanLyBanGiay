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
public class User {
    private String id;
    private String id_employee;
    private String account;
    private String password;
    private String id_role;

    public User() {
    }

    public User(String id, String id_employee, String account, String password, String id_role) {
        this.id = id;
        this.id_employee = id_employee;
        this.account = account;
        this.password = password;
        this.id_role = id_role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_employee() {
        return id_employee;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }
    
    
    
}
