/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.entity.Employees;
import com.sma.entity.Role;
import com.sma.view.EmployeeManagementView;
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
public class EmployeesController {

    public static List<Employees> getAllEmployees() {
        List<Employees> listEmp = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT * FROM employees";
        Statement st;
        ResultSet rs;

        try {
            conn = JDBCConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            Employees employees = null;
            while (rs.next()) {
                employees = new Employees();

                employees.setId(rs.getString("id_employee"));
                employees.setName(rs.getString("name"));
                employees.setPhone(rs.getString("phone"));
                employees.setGender(rs.getString("gender"));
                employees.setDayOfBirth(rs.getString("dayOfBirth"));
                employees.setAddress(rs.getString("address"));
                employees.setStartWorkingDay(rs.getString("startWorkingDay"));
                employees.setPosition(rs.getString("position"));
                employees.setSalary(rs.getDouble("salary"));
                listEmp.add(employees);
            }
            conn.close();
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmp;
    }

    public static void addEmployee(Employees emp) {
        String querry = "INSERT INTO employees (id_employee, name, phone, gender, dayOfBirth, address, startWorkingDay, position, salary) "
                + "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,?)";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getGender());
            ps.setString(5, emp.getDayOfBirth());
            ps.setString(6, emp.getAddress());
            ps.setString(7, emp.getStartWorkingDay());
            ps.setString(8, emp.getPosition());
            ps.setDouble(9, emp.getSalary());

            ps.executeUpdate();
            System.out.println("da luu");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteEmployee(String id_employee) {
        String querry = "DELETE FROM employees WHERE (id_employee = '" + id_employee + "')";
        int rowDelete = 0;
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            rowDelete = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean updateEmployee(Employees emp, String id_employee) {
        Connection conn = null;
        String querry = "UPDATE employees SET name = ?, phone = ?, gender = ?, dayOfBirth = ?, address = ?, startWorkingDay = ?, position = ?, salary = ?"
                + "  where (id_employee = '" + id_employee + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPhone());
            ps.setString(3, emp.getGender());
            ps.setString(4, emp.getDayOfBirth());
            ps.setString(5, emp.getAddress());
            ps.setString(6, emp.getStartWorkingDay());
            ps.setString(7, emp.getPosition());
            ps.setDouble(8, emp.getSalary());

            System.out.println("da cap nhat");
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    public static List<Employees> searchEmp(String infoEmp) {
        List<Employees> listSearchEmp = new ArrayList<>();
        String querry = " SELECT * FROM employees WHERE id_employee like ? or name  like ? or phone like ?";

        //String querry = "SELECT id_producer FROM producers";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ps.setString(1, "%" + infoEmp + "%");
            ps.setString(2, "%" + infoEmp + "%");
             ps.setString(3, "%" + infoEmp + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employees emp = new Employees();

                emp.setId(rs.getString("id_employee"));
                emp.setName(rs.getString("name"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setDayOfBirth(rs.getString("dayOfBirth"));
                emp.setAddress(rs.getString("address"));
                emp.setStartWorkingDay(rs.getString("startWorkingDay"));
                emp.setPosition(rs.getString("position"));
                emp.setSalary(rs.getDouble("salary"));

                listSearchEmp.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSearchEmp;
    }
}
