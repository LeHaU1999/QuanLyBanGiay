/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.dto.IdEmpDto;
import com.sma.entity.Login;
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
public class LoginController {


//    public static boolean loginToSystem(Login login) {
//        Connection conn = null;
//        List<Login> listLogin = new ArrayList<>();
//        String querry = "SELECT * FROM users WHERE accounts =? and passwords = ?  and id_role = ?";
//        PreparedStatement st;
//        ResultSet rs = null;
//        try {
//            conn = JDBCConnection.getConnection();
//            st = conn.prepareStatement(querry);
//            System.out.println("st");
//
//            while (rs.next()) {
//                login = new Login();
//                System.out.println("1");
//                login.setAccount(rs.getString("accounts"));
//                login.setPasword(rs.getString("passwords"));
//                login.setRole(rs.getString("id_role"));
//                listLogin.add(login);
//                rs = st.executeQuery();
//                System.out.println("rs");
//                System.out.println("login");
//                return true;
//            }
//            System.out.println("login 2");
//            conn.close();
//            rs.close();
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println("login 3");
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("login 4");
//        return false;
//    }
    public static List<IdEmpDto> getIdEmp(String id_emp) {
        List<IdEmpDto> listIdEmp  = new ArrayList<>();
        String querry = "select id_employee from users where (accounts = '"+id_emp+"')";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            System.out.println("id emp 1");
            //ps.setString(1, "'" + id_emp + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IdEmpDto empDto = new IdEmpDto();
                empDto.setId_emp(rs.getString("id_employee"));
                System.out.println("id emp 2");
                listIdEmp.add(empDto);
                System.out.println("id emp 3");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIdEmp;
    }
}
