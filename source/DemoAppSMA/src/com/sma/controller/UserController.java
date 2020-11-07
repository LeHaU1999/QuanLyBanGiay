/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.dto.InfoEmpDto;
import com.sma.entity.Role;
import com.sma.entity.User;
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
public class UserController {

    public static ArrayList<String> loadDataToCombobox() {
        ArrayList<String> list = new ArrayList<String>();
        Connection conn = null;
        String querry = "SELECT description FROM roles";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<User> getAllUser() {
        List<User> listUser = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM users";
        Statement st;
        ResultSet rs;
        try {
            conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id_user"));
                user.setId_employee(rs.getString("id_employee"));
                user.setAccount(rs.getString("accounts"));
                user.setPassword(rs.getString("passwords"));
                user.setId_role(rs.getString("id_role"));
                listUser.add(user);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUser;
    }

    public static void addUser(User user) {
        Connection conn = null;
        String querry = "INSERT INTO users (id_user, id_employee, accounts, passwords, id_role)  VALUES (?, ?, ?, ?, ?)";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, user.getId());
            ps.setString(2, user.getId_employee());
            ps.setString(3, user.getAccount());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getId_role());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteUser(String id_user) {
        Connection conn = null;
        String querry = "DELETE FROM users WHERE (id_user = '" + id_user + "')";
        PreparedStatement ps = null;
        int rowDelete = 0;
        try {
            conn = JDBCConnection.getConnection();
            ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean updateUser(User user, String id_user) {
        Connection conn = null;
        String querry = "UPDATE users SET accounts = ? , passwords = ? , id_role = ?  WHERE (id_user = '" + id_user + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getId_role());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean checkUsername(String account) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM users WHERE (accounts = '" + account + "')";

        try {
            ps = JDBCConnection.getConnection().prepareStatement(query);
            //ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkUser;
    }
    
    public static boolean checkEmp(String id_employee) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM id_employee WHERE (id_employee = '" + id_employee + "')";

        try {
            ps = JDBCConnection.getConnection().prepareStatement(query);
            //ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkUser;
    }

    public static List<InfoEmpDto> getInfoUser() {
        List<InfoEmpDto> listInfo = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM employees";
        Statement st;
        ResultSet rs;
        try {
            conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            while (rs.next()) {
                InfoEmpDto info = new InfoEmpDto();
                info.setId(rs.getString("id_employee"));
                info.setName(rs.getString("name"));
                listInfo.add(info);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInfo;
    }
}
