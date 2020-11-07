package com.sma.entity;

public class Bill {

    private String id;
    private String id_customer;
    private String nameCustomer;
    private String sdt;
    private String id_employee;
    private double totalMoney;
    private String dayBill;

    public Bill() {
    }

    public Bill(String id, String id_customer, String nameCustomer, String sdt, String id_employee, double totalMoney, String dayBill) {
        this.id = id;
        this.id_customer = id_customer;
        this.nameCustomer = nameCustomer;
        this.sdt = sdt;
        this.id_employee = id_employee;
        this.totalMoney = totalMoney;
        this.dayBill = dayBill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getId_employee() {
        return id_employee;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDayBill() {
        return dayBill;
    }

    public void setDayBill(String dayBill) {
        this.dayBill = dayBill;
    }
    

}
