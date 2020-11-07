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
public class ReportBillDto {

    private String dayBill;
    private String id_emp;
    private String id_bill;
    private double totalMoney;

    public ReportBillDto() {
    }

    public ReportBillDto(String dayBill, String id_emp, String id_bill, double totalMoney) {
        this.dayBill = dayBill;
        this.id_emp = id_emp;
        this.id_bill = id_bill;
        this.totalMoney = totalMoney;
    }

    public String getDayBill() {
        return dayBill;
    }

    public void setDayBill(String dayBill) {
        this.dayBill = dayBill;
    }

    public String getId_emp() {
        return id_emp;
    }

    public void setId_emp(String id_emp) {
        this.id_emp = id_emp;
    }

    public String getId_bill() {
        return id_bill;
    }

    public void setId_bill(String id_bill) {
        this.id_bill = id_bill;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

}
