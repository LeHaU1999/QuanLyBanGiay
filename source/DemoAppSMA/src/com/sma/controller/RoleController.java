/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.entity.Role;
import com.sma.view.RoleView;
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
public class RoleController {

    public static List<Role> getAllRole() {

        List<Role> listRole = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM roles";
        Statement st;
        ResultSet rs;

        try {
            conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            Role role = null;
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getString("id_role"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));

                listRole.add(role);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRole;
    }

    public static void addRole(Role role) {
        Connection conn = null;
        String querry = "INSERT INTO roles (id_role, name, description)  VALUES (?, ?, ?)";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, role.getId());
            ps.setString(2, role.getName());
            ps.setString(3, role.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteRole(String id_role) {
        Connection conn = null;
        String querry = "DELETE FROM roles WHERE id_role=" + id_role;
        PreparedStatement ps = null;
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean updateRole(Role role, String id_role) {
        Connection conn = null;
        String querry = "UPDATE roles SET name = ? , description = ?  where id_role =  " + id_role;
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);

            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
