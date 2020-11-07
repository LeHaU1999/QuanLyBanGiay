/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.entity.Producer;
import com.sma.entity.Role;
import com.sma.view.ProducerView;
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
public class ProducerController {

    public static List<Producer> getAllProducer() {

        List<Producer> listProducer = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM producers";
        Statement st;
        ResultSet rs;

        try {
            conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            Producer producer = null;
            while (rs.next()) {
                producer = new Producer();
                producer.setId(rs.getString("id_producer"));
                producer.setName(rs.getString("name"));

                listProducer.add(producer);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProducerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducer;
    }

    public static void addProducer(Producer producer) {
        Connection conn = null;
        String querry = "INSERT INTO producers (id_producer, name)  VALUES (?, ?)";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, producer.getId());
            ps.setString(2, producer.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProducerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteProducer(String id_producer) {
        Connection conn = null;
        String querry = "DELETE FROM producers WHERE (id_producer= '" + id_producer + "')";
        PreparedStatement ps = null;
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProducerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean updateProducer(Producer producer, String id_producer) {
        Connection conn = null;
        String querry = "UPDATE producers SET name = ?  WHERE (id_producer= '" + id_producer + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, producer.getName());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProducerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
