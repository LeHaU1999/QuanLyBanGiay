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
public class Product {

    private String id;
    private String name;
    private int size;
    private int count;
    private String type;
    private String unit;
    private double moneyInput;
    private double moneyOutput;
    private String ngayNhap;
    private String id_producer;

    public Product() {
    }

    public Product(String id, String name, int size, int count, String type, String unit, double moneyInput, double moneyOutput, String ngayNhap, String id_producer) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.count = count;
        this.type = type;
        this.unit = unit;
        this.moneyInput = moneyInput;
        this.moneyOutput = moneyOutput;
        this.ngayNhap = ngayNhap;
        this.id_producer = id_producer;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMoneyInput() {
        return moneyInput;
    }

    public void setMoneyInput(double moneyInput) {
        this.moneyInput = moneyInput;
    }

    public double getMoneyOutput() {
        return moneyOutput;
    }

    public void setMoneyOutput(double moneyOutput) {
        this.moneyOutput = moneyOutput;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getId_producer() {
        return id_producer;
    }

    public void setId_producer(String id_producer) {
        this.id_producer = id_producer;
    }

}
