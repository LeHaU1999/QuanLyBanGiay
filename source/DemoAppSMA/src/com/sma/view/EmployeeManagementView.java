/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import com.sma.config.JDBCConnection;
import com.sma.controller.EmployeesController;
import com.sma.controller.RoleController;
import com.sma.entity.Employees;
import com.sma.entity.Role;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class EmployeeManagementView extends javax.swing.JFrame {

    String fillName = null;
    byte[] person_image = null;

    public EmployeeManagementView() {
        initComponents();
        showListEmployeesInTable();
        textfieldEnableFalse();

        this.setLocationRelativeTo(null);
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void showListEmployeesInTable() {
        List<Employees> listEmp = EmployeesController.getAllEmployees();
        DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
        Object[] object = new Object[9];
        // System.out.println("oblect");
        for (int i = 0; i < listEmp.size(); i++) {
            object[0] = listEmp.get(i).getId();
            object[1] = listEmp.get(i).getName();
            object[2] = listEmp.get(i).getPhone();
            object[3] = listEmp.get(i).getGender();
            object[4] = listEmp.get(i).getDayOfBirth();
            object[5] = listEmp.get(i).getAddress();
            object[6] = listEmp.get(i).getStartWorkingDay();
            object[7] = listEmp.get(i).getPosition();
            object[8] = listEmp.get(i).getSalary();
            model.addRow(object);
        }
    }

    public void autoId() {
        Connection conn = null;
        try {
            conn = JDBCConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Max(id_employee) FROM employees");
            rs.next();
            if (rs.getString("Max(id_employee)") == null) {
                txtID.setText("EMP001");
                txtID.setEditable(false);
            } else {
                int id = Integer.parseInt(rs.getString("Max(id_employee)").substring(5, rs.getString("Max(id_employee)").length()));
                id++;
                txtID.setText("EMP00" + id);
                txtID.setEditable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void textfieldEnableFalse() {
        txtID.setEditable(false);
        txtName.setEditable(false);
        txtPhone.setEditable(false);
        radNam.setSelected(false);
        radNu.setSelected(false);
        txtAddress.setEditable(false);
        cboPosition.setSelectedItem(null);
        txtSalary.setEditable(false);
        jdcDOB.setDate(null);
        jdcSWD.setDate(null);
    }

    public void textfieldEnableTrue() {
        txtName.setEditable(true);
        txtPhone.setEditable(true);
        txtAddress.setEditable(true);
        cboPosition.setSelectedItem(null);
        txtSalary.setEditable(true);
        jdcDOB.setDate(null);
        jdcSWD.setDate(null);
    }

    public void XuatDanhSachNhanVien() {
        File file = new File("danhsachnhanvien.txt");
        file.delete();
        int rowCount = tblEmployees.getRowCount();
        System.out.println(rowCount);

        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("danhsachnhanvien.txt"), "UTF8"));
            b.write("\t\t\t\t\t\t\tDANH SACH NHAN VIEN\n");
            b.write("\t\t\t\t\t\t\t*******************\n");
            b.write("\n");
            b.write("\tTổng Số Nhân Viên: " + String.valueOf(rowCount) + "\n");
            b.write("\n------------------------------------------------------------------------------------------------------------------------------------------\n");

            b.write("   " + "STT" + "\t" + "ID_Emp" + "\t" + "NAME_Emp" + "\t" + "Phone Number" + "\t" + "Gender" + "\t" + "DayOfBirth" + "\t" + "Address" + "\t\t\t" + "StartWorđay" + "\t" + "Position" + "\t" + "Salary" + "\n");

            int index = tblEmployees.getRowCount();
            for (int i = 0; i < index; i++) {
                String a0 = String.valueOf(i + 1);
                String a1 = (String) tblEmployees.getValueAt(i, 0);
                String a2 = (String) tblEmployees.getValueAt(i, 1);
                String a3 = (String) tblEmployees.getValueAt(i, 2);
                String a4 = (String) tblEmployees.getValueAt(i, 3);
                String a5 = (String) tblEmployees.getValueAt(i, 4);
                String a6 = (String) tblEmployees.getValueAt(i, 5);
                String a7 = (String) tblEmployees.getValueAt(i, 6);
                String a8 = (String) tblEmployees.getValueAt(i, 7);

                double a9 = (double) tblEmployees.getValueAt(0, 8);
                String aa9 = String.valueOf(a9);

                String s0 = a0;
                String s1 = a1;
                String s2 = a2;
                String s3 = a3;
                String s4 = a4;
                String s5 = a5;
                String s6 = a6;
                String s7 = a7;
                String s8 = a8;
                String s9 = aa9;
                b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
                b.write("   " + s0 + "\t" + s1 + "\t" + s2 + "\t" + s3 + "\t" + s4 + "\t" + s5 + "\t" + s6 + "\t" + s7 + "\t" + s8 + "\t" + s9 + "\n");
            }
            b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  danhsachnhanvien.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xuất CC, Lỗi rồi");
        }
    }

    public void searchEmployees() {
        List<Employees> listSearchProduct = EmployeesController.searchEmp(txtSearchEmp.getText().trim());
        DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
        int a = 0;
        Object[] object = new Object[9];
        for (int i = 0; i < listSearchProduct.size(); i++) {
            object[0] = listSearchProduct.get(i).getId();
            object[1] = listSearchProduct.get(i).getName();
            object[2] = listSearchProduct.get(i).getPhone();
            object[3] = listSearchProduct.get(i).getGender();
            object[4] = listSearchProduct.get(i).getDayOfBirth();
            object[5] = listSearchProduct.get(i).getAddress();
            object[6] = listSearchProduct.get(i).getStartWorkingDay();
            object[7] = listSearchProduct.get(i).getPosition();
            object[8] = listSearchProduct.get(i).getSalary();
            model.addRow(object);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGender = new javax.swing.ButtonGroup();
        jpnEmployeeManager = new javax.swing.JPanel();
        jpnBanner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnXuatDanhSach = new javax.swing.JButton();
        jpnInformationEmployees = new javax.swing.JPanel();
        jpnDetailInformation = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblgender = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblSWD = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        lblSalary = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
        txtAddress = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jdcDOB = new com.toedter.calendar.JDateChooser();
        jdcSWD = new com.toedter.calendar.JDateChooser();
        btnAddNewEmployees = new javax.swing.JButton();
        lblGoToHomeScreen = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        cboPosition = new javax.swing.JComboBox();
        btnChoose = new javax.swing.JButton();
        jpnListEmployees = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        txtSearchEmp = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jpnEmployeeManager.setBackground(new java.awt.Color(255, 255, 255));

        jpnBanner.setBackground(new java.awt.Color(255, 255, 255));
        jpnBanner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnBanner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH NHÂN VIÊN");

        btnXuatDanhSach.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnXuatDanhSach.setForeground(new java.awt.Color(238, 28, 37));
        btnXuatDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-print.png"))); // NOI18N
        btnXuatDanhSach.setText("XUẤT DANH SÁCH");
        btnXuatDanhSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 76, 128), 2));
        btnXuatDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnBannerLayout = new javax.swing.GroupLayout(jpnBanner);
        jpnBanner.setLayout(jpnBannerLayout);
        jpnBannerLayout.setHorizontalGroup(
            jpnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBannerLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnBannerLayout.setVerticalGroup(
            jpnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBannerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnInformationEmployees.setBackground(new java.awt.Color(255, 255, 255));
        jpnInformationEmployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));

        jpnDetailInformation.setBackground(new java.awt.Color(255, 255, 255));
        jpnDetailInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblId.setText("ID:");

        lblName.setText("Name");

        lblPhone.setText("Phone");

        lblgender.setText("Gender");

        lblDOB.setText("D.O.B");

        lblAddress.setText("Address");

        lblSWD.setText("S.W.D:");

        lblPosition.setText("Position:");

        lblSalary.setText("Salary:");

        btgGender.add(radNam);
        radNam.setText("Nam");

        btgGender.add(radNu);
        radNu.setText("Nữ");

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-save.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-updatepng.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jdcDOB.setBackground(new java.awt.Color(255, 255, 255));

        jdcSWD.setBackground(new java.awt.Color(255, 255, 255));

        btnAddNewEmployees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-add-new-user.png"))); // NOI18N
        btnAddNewEmployees.setText("Add New Employee");
        btnAddNewEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewEmployeesActionPerformed(evt);
            }
        });

        lblGoToHomeScreen.setBackground(new java.awt.Color(0, 76, 128));
        lblGoToHomeScreen.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        lblGoToHomeScreen.setForeground(new java.awt.Color(238, 28, 37));
        lblGoToHomeScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGoToHomeScreen.setText("Go To HomeScreen");
        lblGoToHomeScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoToHomeScreenMouseClicked(evt);
            }
        });

        lblImage.setBackground(new java.awt.Color(255, 255, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("avata");
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cboPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quản Trị Viên", "Quản  Lý", "Nhân Viên", " " }));

        btnChoose.setText("Choose");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnDetailInformationLayout = new javax.swing.GroupLayout(jpnDetailInformation);
        jpnDetailInformation.setLayout(jpnDetailInformationLayout);
        jpnDetailInformationLayout.setHorizontalGroup(
            jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                        .addComponent(btnAddNewEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblId)
                                            .addComponent(lblName)
                                            .addComponent(lblPhone)
                                            .addComponent(lblgender)
                                            .addComponent(lblDOB))
                                        .addGap(23, 23, 23)
                                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                                .addComponent(radNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(radNu))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInformationLayout.createSequentialGroup()
                                                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jdcDOB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtID)
                                                    .addComponent(lblImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(44, 44, 44))))
                                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                                .addComponent(btnSave)
                                                .addGap(15, 15, 15)
                                                .addComponent(btnUpdate)
                                                .addGap(15, 15, 15)
                                                .addComponent(btnDelete))
                                            .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnDetailInformationLayout.createSequentialGroup()
                                                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblPosition)
                                                        .addComponent(lblSalary))
                                                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                                            .addGap(15, 15, 15)
                                                            .addComponent(txtSalary))
                                                        .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                                            .addGap(18, 18, 18)
                                                            .addComponent(cboPosition, 0, 198, Short.MAX_VALUE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnDetailInformationLayout.createSequentialGroup()
                                                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblAddress)
                                                        .addComponent(lblSWD))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtAddress)
                                                        .addComponent(jdcSWD, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))))
                                        .addGap(0, 2, Short.MAX_VALUE)))))
                        .addGap(10, 10, 10))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInformationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChoose)
                .addGap(128, 128, 128))
        );
        jpnDetailInformationLayout.setVerticalGroup(
            jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnChoose)
                .addGap(10, 10, 10)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblgender)
                    .addComponent(radNam)
                    .addComponent(radNu))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDOB)
                    .addComponent(jdcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSWD)
                    .addComponent(jdcSWD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosition)
                    .addComponent(cboPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalary)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddNewEmployees)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jpnListEmployees.setBackground(new java.awt.Color(255, 255, 255));
        jpnListEmployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Phone", "Gender", "D.O.B", "Address", "S.W.D", "Position", "Salary"
            }
        ));
        tblEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployees);
        if (tblEmployees.getColumnModel().getColumnCount() > 0) {
            tblEmployees.getColumnModel().getColumn(0).setMinWidth(70);
            tblEmployees.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblEmployees.getColumnModel().getColumn(0).setMaxWidth(70);
            tblEmployees.getColumnModel().getColumn(3).setMinWidth(60);
            tblEmployees.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblEmployees.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        txtSearchEmp.setText("Search....");
        txtSearchEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchEmpMouseClicked(evt);
            }
        });
        txtSearchEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchEmpActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(238, 28, 37));
        jLabel2.setText("Search employee by id, name or phone number");

        javax.swing.GroupLayout jpnListEmployeesLayout = new javax.swing.GroupLayout(jpnListEmployees);
        jpnListEmployees.setLayout(jpnListEmployeesLayout);
        jpnListEmployeesLayout.setHorizontalGroup(
            jpnListEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
            .addGroup(jpnListEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnListEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnListEmployeesLayout.createSequentialGroup()
                        .addComponent(txtSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnListEmployeesLayout.setVerticalGroup(
            jpnListEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnListEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnListEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnInformationEmployeesLayout = new javax.swing.GroupLayout(jpnInformationEmployees);
        jpnInformationEmployees.setLayout(jpnInformationEmployeesLayout);
        jpnInformationEmployeesLayout.setHorizontalGroup(
            jpnInformationEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInformationEmployeesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jpnDetailInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jpnListEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnInformationEmployeesLayout.setVerticalGroup(
            jpnInformationEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInformationEmployeesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jpnInformationEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnDetailInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnListEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpnEmployeeManagerLayout = new javax.swing.GroupLayout(jpnEmployeeManager);
        jpnEmployeeManager.setLayout(jpnEmployeeManagerLayout);
        jpnEmployeeManagerLayout.setHorizontalGroup(
            jpnEmployeeManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnInformationEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnEmployeeManagerLayout.setVerticalGroup(
            jpnEmployeeManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEmployeeManagerLayout.createSequentialGroup()
                .addComponent(jpnBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jpnInformationEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnEmployeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnEmployeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblGoToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoToHomeScreenMouseClicked
        // TODO add your handling code here:
        HomeScreen home = new HomeScreen();
        home.setVisible(true);
        setVisible(false);
        System.out.println("You have just pressed button 'Go To HomeScreen' ");
    }//GEN-LAST:event_lblGoToHomeScreenMouseClicked

    private void tblEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeesMouseClicked
        try {
            textfieldEnableTrue();
            txtID.setEditable(false);

            int i = tblEmployees.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
            txtID.setText(model.getValueAt(i, 0).toString());
            txtName.setText(model.getValueAt(i, 1).toString());
            txtPhone.setText(model.getValueAt(i, 2).toString());
            String gender = model.getValueAt(i, 3).toString();

            if (gender.equals("Nam")) {
                radNam.setSelected(true);
            } else {
                radNu.setSelected(true);
            }

            Date dateDBO = new SimpleDateFormat("MMM d, yyyy").parse((String) model.getValueAt(i, 4));
            jdcDOB.setDate(dateDBO);

            txtAddress.setText(model.getValueAt(i, 5).toString());

            Date dateSWD = new SimpleDateFormat("MMM d, yyyy").parse((String) model.getValueAt(i, 6));
            jdcSWD.setDate(dateSWD);

            String empPosition = model.getValueAt(i, 7).toString().trim();
            switch (empPosition) {
                case "Quản Trị Viên":
                    cboPosition.setSelectedIndex(0);
                    break;
                case "Quản  Lý":
                    cboPosition.setSelectedIndex(1);
                    break;
                case "Nhân Viên":
                    cboPosition.setSelectedIndex(2);
                    break;
            }

            txtSalary.setText(model.getValueAt(i, 8).toString().trim());

            btnDelete.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnSave.setEnabled(false);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeManagementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblEmployeesMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnAddNewEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewEmployeesActionPerformed
        // TODO add your handling code here:
        autoId();
        txtID.setEditable(false);

        txtName.setEditable(true);
        txtName.setText("");

        txtPhone.setText("");
        txtPhone.setEditable(true);

        radNam.setSelected(true);

        txtAddress.setText("");
        txtAddress.setEditable(true);

        cboPosition.setSelectedIndex(0);

        txtSalary.setText("");
        txtSalary.setEditable(true);

        jdcDOB.setDate(null);
        jdcSWD.setDate(null);

        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnAddNewEmployeesActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {

            String gender = null;
            if (radNam.isSelected()) {
                gender = "Nam";
            } else if (radNu.isSelected()) {
                gender = "Nu";
            }

            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ID Nhân Viên Không Được Bỏ Trống");
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên Nhân Viên Không Được Bỏ Trống");
            } else if (txtPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "SĐT Nhân Viên Không Được Bỏ Trống");
            } else if (txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ Nhân Viên Không Được Bỏ Trống");
            } else if (txtSalary.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lương Nhân Viên Không Được Bỏ Trống");
            } else {
                SimpleDateFormat dateDboFormat = new SimpleDateFormat("MMM d, yyyy");
                String dateBDO = dateDboFormat.format(jdcDOB.getDate());

                SimpleDateFormat dateSwdFormat = new SimpleDateFormat("MMM d, yyyy");
                String dateSWD = dateDboFormat.format(jdcSWD.getDate());

                Employees emp = new Employees();
                emp.setId(txtID.getText());
                emp.setName(txtName.getText());
                emp.setPhone(txtPhone.getText());
                emp.setGender(gender);
                emp.setDayOfBirth(dateBDO);
                emp.setAddress(txtAddress.getText());
                emp.setStartWorkingDay(dateSWD);
                emp.setPosition(cboPosition.getSelectedItem().toString());
                emp.setSalary(Double.parseDouble(txtSalary.getText()));

                EmployeesController.addEmployee(emp);
                JOptionPane.showMessageDialog(null, "Thêm Mới Nhân VIên Thành Công  <_/>");
                DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
                model.setRowCount(0);
                showListEmployeesInTable();

                autoId();
                txtName.setText("");
                txtPhone.setText("");
                radNam.setSelected(false);
                radNu.setSelected(false);
                jdcDOB.setCalendar(null);
                txtAddress.setText("");
                jdcSWD.setCalendar(null);
                cboPosition.setSelectedIndex(0);
                txtSalary.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm mới thất bại, DB0 và SWD không đúng định dạng hoặc bị bỏ trống");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked

    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(null, "Xóa Nhân Viên " + txtName.getText(), "DELETE", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            int rowIndex = tblEmployees.getSelectedRow();
            String id_employee = tblEmployees.getModel().getValueAt(rowIndex, 0).toString().trim();
            try {
                EmployeesController.deleteEmployee(id_employee);
                DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
                model.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Đã Xóa Nhân Viên " + txtName.getText() + " thành công");
                showListEmployeesInTable();
                textfieldEnableFalse();

                btnSave.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Không Thể Xóa Nhân Viên Đã Lập Hóa Đơn, Vui lòng xóa hóa đơn do " + id_employee + " lập trước");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String gender = null;
            if (radNam.isSelected()) {
                gender = "Nam";
            } else if (radNu.isSelected()) {
                gender = "Nu";
            }

            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "ID Nhân Viên Không Được Bỏ Trống");
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên Nhân Viên Không Được Bỏ Trống");
            } else if (txtPhone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "SĐT Nhân Viên Không Được Bỏ Trống");
            } else if (txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ Nhân Viên Không Được Bỏ Trống");
            } else if (txtSalary.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lương Nhân Viên Không Được Bỏ Trống");
            } else {
                SimpleDateFormat dateDboFormat = new SimpleDateFormat("MMM d, yyyy");
                String dateBDO = dateDboFormat.format(jdcDOB.getDate());

                SimpleDateFormat dateSwdFormat = new SimpleDateFormat("MMM d, yyyy");
                String dateSWD = dateDboFormat.format(jdcSWD.getDate());

                Employees emp = new Employees();
                emp.setId(txtID.getText());
                emp.setName(txtName.getText());
                emp.setPhone(txtPhone.getText());
                emp.setGender(gender);
                emp.setDayOfBirth(dateBDO);
                emp.setAddress(txtAddress.getText());
                emp.setStartWorkingDay(dateSWD);
                emp.setPosition(cboPosition.getSelectedItem().toString());
                emp.setSalary(Double.parseDouble(txtSalary.getText()));

                int rowIndex = tblEmployees.getSelectedRow();
                String id_employee = tblEmployees.getModel().getValueAt(rowIndex, 0).toString();
                EmployeesController.updateEmployee(emp, id_employee);
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công");
                DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
                model.setRowCount(0);
                showListEmployeesInTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thất bại, vui lòng kiểm tra lại thông tin");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        // TODO add your handling code here:
        JFileChooser choose = new JFileChooser();
        choose.showOpenDialog(null);
        File file = choose.getSelectedFile();
        fillName = file.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(fillName).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);

        try {
            File image = new File(fillName);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            person_image = bos.toByteArray();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachActionPerformed
        // TODO add your handling code here:
        XuatDanhSachNhanVien();
    }//GEN-LAST:event_btnXuatDanhSachActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();
        model.setRowCount(0);
        searchEmployees();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchEmpActionPerformed
        // TODO add your handling code here:
        if (txtSearchEmp.getText().equals("Search....")) {
            txtSearchEmp.setText("");
        } else {
            txtSearchEmp.setText(txtSearchEmp.getText());
        }
    }//GEN-LAST:event_txtSearchEmpActionPerformed

    private void txtSearchEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchEmpMouseClicked
        // TODO add your handling code here:
        if (txtSearchEmp.getText().equals("Search....")) {
            txtSearchEmp.setText("");
        } else {
            txtSearchEmp.setText(txtSearchEmp.getText());
        }
    }//GEN-LAST:event_txtSearchEmpMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGender;
    private javax.swing.JButton btnAddNewEmployees;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXuatDanhSach;
    private javax.swing.JComboBox cboPosition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcDOB;
    private com.toedter.calendar.JDateChooser jdcSWD;
    private javax.swing.JPanel jpnBanner;
    private javax.swing.JPanel jpnDetailInformation;
    private javax.swing.JPanel jpnEmployeeManager;
    private javax.swing.JPanel jpnInformationEmployees;
    private javax.swing.JPanel jpnListEmployees;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblGoToHomeScreen;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblSWD;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lblgender;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearchEmp;
    // End of variables declaration//GEN-END:variables
}
