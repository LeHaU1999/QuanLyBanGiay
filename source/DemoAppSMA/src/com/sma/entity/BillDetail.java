/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.entity;

public class BillDetail {
    private String id_bill;
    private String id_product;
    private String type;
    private int size;
    private int count;
    private double donGia;

    public BillDetail() {
    }

    public BillDetail(String id_bill, String id_product, String type, int size, int count, double donGia) {
        this.id_bill = id_bill;
        this.id_product = id_product;
        this.type = type;
        this.size = size;
        this.count = count;
        this.donGia = donGia;
    }

    public String getId_bill() {
        return id_bill;
    }

    public void setId_bill(String id_bill) {
        this.id_bill = id_bill;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    
}
