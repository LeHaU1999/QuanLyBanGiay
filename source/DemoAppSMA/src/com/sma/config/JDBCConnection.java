/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class JDBCConnection {

    public static Connection getConnection() {

        final String url = "jdbc:mysql://localhost:3306/suport_management_application";
        final String user = "root";
        final String password = "lengochau1999";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("ket noi thanh cong: " +conn);
                //System.out.println(conn);
                return conn;
        } catch (ClassNotFoundException e) {
             System.out.println("ket noi that bai: ");
            e.printStackTrace();
        }catch(SQLException e){
            System.out.println("khong tim thay driver");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
