/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.entity.Bill;
import com.sma.entity.BillDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillDetailController {

    public static List<Bill> getAllInvoice() {
        List<Bill> listInvoice = new ArrayList<>();
        String querry = "SELECT * FROM bills";
        Statement st;
        ResultSet rs;

        try {
            Connection conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            while (rs.next()) {
                Bill invoice = new Bill();
                invoice.setId(rs.getString("id_bill"));
                invoice.setId_customer(rs.getString("id_customer"));
                invoice.setNameCustomer(rs.getString("nameCustomer"));
                invoice.setSdt(rs.getString("sdt"));
                invoice.setId_employee(rs.getString("id_employee"));
                invoice.setTotalMoney(rs.getDouble("totalMoney"));
                invoice.setDayBill(rs.getString("dayBill"));

                listInvoice.add(invoice);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInvoice;
    }

    public static void addInvoice(Bill invoice) {
        Connection conn = null;
        String querry = "INSERT INTO bills (id_bill, id_customer, nameCustomer, sdt, id_employee, totalMoney)  VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, invoice.getId());
            ps.setString(2, invoice.getId_employee());
            ps.setString(3, invoice.getId_customer());
            ps.setString(4, invoice.getNameCustomer());
            ps.setString(5, invoice.getSdt());
            ps.setDouble(6, invoice.getTotalMoney());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteInvoice(String id_bill) {
        Connection conn = null;
        String querry = "DELETE FROM bills WHERE (id_bill = '" + id_bill + "')";
        PreparedStatement ps = null;
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<BillDetail> getAllBill_Detail(String id_bill) {
        List<BillDetail> listBill_Detail = new ArrayList<>();
        String querry = "SELECT * FROM bill_detail WHERE (id_bill = '" + id_bill + "')";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            System.out.println("id emp 1");
            //ps.setString(1, "'" + id_emp + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillDetail bill_detail = new BillDetail();
                bill_detail.setId_bill(rs.getString("id_bill"));
                bill_detail.setId_product(rs.getString("id_product"));
                bill_detail.setType(rs.getString("type"));
                bill_detail.setSize(rs.getInt("size"));
                bill_detail.setCount(rs.getInt("count"));
                bill_detail.setDonGia(rs.getDouble("donGia"));

                listBill_Detail.add(bill_detail);
                System.out.println("id emp 3");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBill_Detail;
    }

    public static void deleteBill_Detail(String id_bill) {
        Connection conn = null;
        String querry = "DELETE FROM bill_detail WHERE (id_bill = '" + id_bill + "')";
        PreparedStatement ps = null;
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Bill> getBillByInfo(String infoBill) {
        List<Bill> listSearchBill = new ArrayList<>();
        String querry = "SELECT * FROM bills WHERE  dayBill LIKE ? OR nameCustomer LIKE ? or sdt LIKE?";

        //String querry = "SELECT id_producer FROM producers";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, "%" + infoBill + "%");
            ps.setString(2, "%" + infoBill + "%");
            ps.setString(3, "%" + infoBill + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill searchBill = new Bill();

                searchBill.setDayBill(rs.getString("dayBill"));
                searchBill.setId(rs.getString("id_bill"));
                searchBill.setId_customer(rs.getString("id_customer"));
                searchBill.setNameCustomer(rs.getString("nameCustomer"));
                searchBill.setSdt(rs.getString("sdt"));
                searchBill.setId_employee(rs.getString("id_employee"));
                searchBill.setTotalMoney(rs.getDouble("totalMoney"));

                listSearchBill.add(searchBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSearchBill;
    }
}
