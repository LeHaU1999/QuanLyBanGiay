/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.dto;

/**
 *
 * @author ASUS
 */
public class SearchProductDto {
    private String id;
    private String name;
    private int size;
    private int count;
    private String unit;
    private String type;
    private double moneyOutput;

    public SearchProductDto() {
    }

    public SearchProductDto(String id, String name, int size, int count, String unit, String type, double moneyOutput) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.count = count;
        this.unit = unit;
        this.type = type;
        this.moneyOutput = moneyOutput;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoneyOutput() {
        return moneyOutput;
    }

    public void setMoneyOutput(double moneyOutput) {
        this.moneyOutput = moneyOutput;
    }
    
    
    
}
