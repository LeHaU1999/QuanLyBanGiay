/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.entity.Product;
import com.sma.view.ProductManagementView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductController {

    public static ArrayList<String> loadDataToCombobox() {
        ArrayList<String> list = new ArrayList<String>();
        Connection conn = null;
        String querry = "SELECT id_producer FROM producers";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("id_producer"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<Product> getDataByGiayNam() {
        ArrayList<Product> listGiayNam = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM products WHERE type='Giày Nam'";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id_product"));
                product.setName(rs.getString("name"));
                product.setSize(rs.getInt("size"));
                product.setCount(rs.getInt("count"));
                product.setType(rs.getString("type"));
                product.setUnit(rs.getString("unit"));
                product.setMoneyInput(rs.getDouble("moneyInput"));
                product.setMoneyOutput(rs.getDouble("moneyOutput"));
                product.setNgayNhap(rs.getString("ngayNhap"));
                product.setId_producer(rs.getString("id_producer"));

                listGiayNam.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGiayNam;
    }

    public static ArrayList<Product> getDataByGiayNu() {
        ArrayList<Product> listGiayNu = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM products WHERE type='Giày Nữ'";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id_product"));
                product.setName(rs.getString("name"));
                product.setSize(rs.getInt("size"));
                product.setCount(rs.getInt("count"));
                product.setType(rs.getString("type"));
                product.setUnit(rs.getString("unit"));
                product.setMoneyInput(rs.getDouble("moneyInput"));
                product.setMoneyOutput(rs.getDouble("moneyOutput"));
                product.setNgayNhap(rs.getString("ngayNhap"));
                product.setId_producer(rs.getString("id_producer"));

                listGiayNu.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGiayNu;
    }

    public static void addProduct(Product product) {
        Connection conn = null;
        String querry = "INSERT INTO products (id_product, name, size, count, type, unit, moneyInput, moneyOutput, ngayNhap, id_producer) "
                + "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getSize());
            ps.setInt(4, product.getCount());
            ps.setString(5, product.getType());
            ps.setString(6, product.getUnit());
            ps.setDouble(7, product.getMoneyInput());
            ps.setDouble(8, product.getMoneyOutput());
            ps.setString(9, product.getNgayNhap());
            ps.setString(10, product.getId_producer());
            ps.executeUpdate();
            System.out.println("da luu");
        } catch (SQLException ex) {
            Logger.getLogger(ProducerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteProduct(String id_product) {
        Connection conn = null;
        String querry = "DELETE FROM products WHERE (id_product = '"+id_product+"')";
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
            System.out.println("da xoa");
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean updateProduct(Product product, String id_product) {
        Connection conn = null;
        String querry = "UPDATE products SET name=?, size=?, count=?, type=?, unit=?, moneyInput=?, moneyOutput=?, ngayNhap=?, id_producer=?"
                + "  where (id_product = '" + id_product + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);

            ps.setString(1, product.getName());
            ps.setInt(2, product.getSize());
            ps.setInt(3, product.getCount());
            ps.setString(4, product.getType());
            ps.setString(5, product.getUnit());
            ps.setDouble(6, product.getMoneyInput());
            ps.setDouble(7, product.getMoneyOutput());
            ps.setString(8, product.getNgayNhap());
            ps.setString(9, product.getId_producer());

            System.out.println("da cap nhat");
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
