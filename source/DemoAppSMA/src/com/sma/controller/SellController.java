package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.dto.SearchProductDto;
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

public class SellController {

    public static List<SearchProductDto> getProductByName(String infoProduct) {
        List<SearchProductDto> listSearchProduct = new ArrayList<>();
        String querry = "SELECT id_product, name, size ,count ,unit ,type ,moneyOutput FROM products WHERE name LIKE ?  OR id_product LIKE ?";

        //String querry = "SELECT id_producer FROM producers";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, "%" + infoProduct + "%");
            ps.setString(2, "%" + infoProduct + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SearchProductDto searchProduct = new SearchProductDto();

                searchProduct.setId(rs.getString("id_product"));
                searchProduct.setName(rs.getString("name"));
                searchProduct.setSize(rs.getInt("size"));
                searchProduct.setCount(rs.getInt("count"));
                searchProduct.setUnit(rs.getString("unit"));
                searchProduct.setType(rs.getString("type"));
                searchProduct.setMoneyOutput(rs.getDouble("moneyOutput"));

                listSearchProduct.add(searchProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSearchProduct;
    }

    public static void addBill(Bill bill) {
        String querry = "INSERT INTO bills (dayBill, id_bill, id_customer, nameCustomer, sdt, id_employee, totalMoney) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, bill.getDayBill());
            ps.setString(2, bill.getId());
            ps.setString(3, bill.getId_customer());
            ps.setString(4, bill.getNameCustomer());
            ps.setString(5, bill.getSdt());
            ps.setString(6, bill.getId_employee());
            ps.setDouble(7, bill.getTotalMoney());
            ps.executeUpdate();
            System.out.println("da luu bill");
        } catch (SQLException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addBill_detail(BillDetail bill_detail) {
        String querry = "INSERT INTO bill_detail (id_bill, id_product, type, size, count, donGia) "
                + "VALUES (? ,? ,? ,? ,? ,? )";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, bill_detail.getId_bill());
            ps.setString(2, bill_detail.getId_product());
            ps.setString(3, bill_detail.getType());
            ps.setInt(4, bill_detail.getSize());
            ps.setInt(5, bill_detail.getCount());
            ps.setDouble(6, bill_detail.getDonGia());
            ps.executeUpdate();
            System.out.println("da luu bill_detail");
        } catch (SQLException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
