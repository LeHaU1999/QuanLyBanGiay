package com.sma.controller;

import com.sma.config.JDBCConnection;
import com.sma.dto.ReportBillDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportController {

    public static ArrayList<String> loadDataDateToComboBox() {
        ArrayList<String> list = new ArrayList<String>();
        String querry = "SELECT dayBill FROM bills";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("dayBill"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList<String> loadDataEmpToComboBox() {
        ArrayList<String> list = new ArrayList<String>();
        String querry = "SELECT id_employee FROM bills";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("id_employee"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<ReportBillDto> getBillReport() {
        List<ReportBillDto> listReportEmp = new ArrayList<>();
        String querry = "SELECT  dayBill ,id_employee ,id_bill ,totalMoney FROM bills";
        try {
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReportBillDto reportBill = new ReportBillDto();
                reportBill.setDayBill(rs.getString("dayBill"));
                reportBill.setId_emp(rs.getString("id_employee"));
                reportBill.setId_bill(rs.getString("id_bill"));
                reportBill.setTotalMoney(rs.getDouble("totalMoney"));
                listReportEmp.add(reportBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReportEmp;
    }

    public static ArrayList<ReportBillDto> getDataByDay(String day) {
        ArrayList<ReportBillDto> list = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT dayBill, id_employee, id_bill, totalMoney FROM bills WHERE (dayBill = '" + day + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReportBillDto listBill = new ReportBillDto();
                listBill.setDayBill(rs.getString("dayBill"));
                listBill.setId_emp(rs.getString("id_employee"));
                listBill.setId_bill(rs.getString("id_bill"));
                listBill.setTotalMoney(rs.getDouble("totalMoney"));

                list.add(listBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public static ArrayList<ReportBillDto> getDataByEmp(String emp) {
        ArrayList<ReportBillDto> list = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT dayBill, id_employee, id_bill, totalMoney FROM bills WHERE (id_employee = '" + emp + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReportBillDto listBill = new ReportBillDto();
                listBill.setDayBill(rs.getString("dayBill"));
                listBill.setId_emp(rs.getString("id_employee"));
                listBill.setId_bill(rs.getString("id_bill"));
                listBill.setTotalMoney(rs.getDouble("totalMoney"));

                list.add(listBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
     public static ArrayList<ReportBillDto> getDataByDayAndEmp(String day , String emp) {
        ArrayList<ReportBillDto> list = new ArrayList<>();
        Connection conn = null;
        String querry = "SELECT dayBill, id_employee, id_bill, totalMoney FROM bills WHERE (dayBill = '" + day + "') AND  (id_employee = '" + emp + "')";
        try {
            conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReportBillDto listBill = new ReportBillDto();
                listBill.setDayBill(rs.getString("dayBill"));
                listBill.setId_emp(rs.getString("id_employee"));
                listBill.setId_bill(rs.getString("id_bill"));
                listBill.setTotalMoney(rs.getDouble("totalMoney"));

                list.add(listBill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
