/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class HomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form HomeScreen
     */
    public HomeScreen() {
        initComponents();
        showDay();
        showTime();
        showInfoUser();
        this.setLocationRelativeTo(null);
    }

    void showDay() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        lblDate.setText(dateFormat.format(date));
    }

    void showTime() {
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                lblTimme.setText(dateFormat.format(date));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).start();
    }

    public void showInfoUser(){
        LoginView loginView = new LoginView();
        lblUser.setText("UName: "+loginView.userName);
        lblRoleName.setText("Role: "+loginView.roleName);
        lblId.setText("ID: "+loginView.id_emp);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnHomeScreen = new javax.swing.JPanel();
        lblHomeScreen = new javax.swing.JLabel();
        jpnQuanLyNhanVien = new javax.swing.JPanel();
        lblIconQLNV = new javax.swing.JLabel();
        lblQLNV = new javax.swing.JLabel();
        jpnHoaDon = new javax.swing.JPanel();
        lblIconSell = new javax.swing.JLabel();
        lblSell = new javax.swing.JLabel();
        jpnQuanLySanPham = new javax.swing.JPanel();
        lbliconQLK = new javax.swing.JLabel();
        lblQuanLyKho = new javax.swing.JLabel();
        jpnInfoUser = new javax.swing.JPanel();
        lblIconInfoUser = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblRoleName = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jpnDayAndTime = new javax.swing.JPanel();
        lblDay = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTimme = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jpRole = new javax.swing.JPanel();
        lblIconRole = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 76, 128));

        jpnHomeScreen.setBackground(new java.awt.Color(255, 255, 255));
        jpnHomeScreen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 76, 128), 5));
        jpnHomeScreen.setForeground(new java.awt.Color(0, 76, 128));
        jpnHomeScreen.setVerifyInputWhenFocusTarget(false);

        lblHomeScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHomeScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/sma.jpg"))); // NOI18N
        lblHomeScreen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jpnQuanLyNhanVien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jpnQuanLyNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpnQuanLyNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnQuanLyNhanVienMouseClicked(evt);
            }
        });

        lblIconQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-ManagenmentUser.png"))); // NOI18N

        lblQLNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQLNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQLNV.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jpnQuanLyNhanVienLayout = new javax.swing.GroupLayout(jpnQuanLyNhanVien);
        jpnQuanLyNhanVien.setLayout(jpnQuanLyNhanVienLayout);
        jpnQuanLyNhanVienLayout.setHorizontalGroup(
            jpnQuanLyNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyNhanVienLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblIconQLNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jpnQuanLyNhanVienLayout.setVerticalGroup(
            jpnQuanLyNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQuanLyNhanVienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpnQuanLyNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jpnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnHoaDonMouseClicked(evt);
            }
        });

        lblIconSell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-sells.png"))); // NOI18N

        lblSell.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblSell.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSell.setText("HÓA ĐƠN");

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconSell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSell, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIconSell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnQuanLySanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnQuanLySanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnQuanLySanPhamMouseClicked(evt);
            }
        });

        lbliconQLK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbliconQLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-box.png"))); // NOI18N

        lblQuanLyKho.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblQuanLyKho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuanLyKho.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout jpnQuanLySanPhamLayout = new javax.swing.GroupLayout(jpnQuanLySanPham);
        jpnQuanLySanPham.setLayout(jpnQuanLySanPhamLayout);
        jpnQuanLySanPhamLayout.setHorizontalGroup(
            jpnQuanLySanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLySanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbliconQLK)
                .addGap(10, 10, 10)
                .addComponent(lblQuanLyKho)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLySanPhamLayout.setVerticalGroup(
            jpnQuanLySanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbliconQLK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblQuanLyKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnInfoUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIconInfoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconInfoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-user.png"))); // NOI18N
        lblIconInfoUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 76, 128)));

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 76, 128));
        lblUser.setText("User: xxx");

        lblRoleName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRoleName.setForeground(new java.awt.Color(238, 28, 37));
        lblRoleName.setText("Role: ???");

        lblId.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblId.setText("ID:");

        javax.swing.GroupLayout jpnInfoUserLayout = new javax.swing.GroupLayout(jpnInfoUser);
        jpnInfoUser.setLayout(jpnInfoUserLayout);
        jpnInfoUserLayout.setHorizontalGroup(
            jpnInfoUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnInfoUserLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblIconInfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jpnInfoUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(lblRoleName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnInfoUserLayout.setVerticalGroup(
            jpnInfoUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoUserLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpnInfoUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIconInfoUser)
                    .addGroup(jpnInfoUserLayout.createSequentialGroup()
                        .addComponent(lblId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUser)
                        .addGap(5, 5, 5)
                        .addComponent(lblRoleName, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jpnDayAndTime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-calendar.png"))); // NOI18N

        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-clock.png"))); // NOI18N

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lblTimme.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout jpnDayAndTimeLayout = new javax.swing.GroupLayout(jpnDayAndTime);
        jpnDayAndTime.setLayout(jpnDayAndTimeLayout);
        jpnDayAndTimeLayout.setHorizontalGroup(
            jpnDayAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDayAndTimeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnDayAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnDayAndTimeLayout.createSequentialGroup()
                        .addComponent(lblDay)
                        .addGap(18, 18, 18)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnDayAndTimeLayout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addGap(18, 18, 18)
                        .addComponent(lblTimme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jpnDayAndTimeLayout.setVerticalGroup(
            jpnDayAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDayAndTimeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpnDayAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDay, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnDayAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblTimme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDangXuat.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-sign-out.png"))); // NOI18N
        btnDangXuat.setText("ĐĂNG XUẤT");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-exit.png"))); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jpRole.setBackground(new java.awt.Color(255, 255, 255));
        jpRole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpRoleMouseClicked(evt);
            }
        });

        lblIconRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconRole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-role-user.png"))); // NOI18N

        javax.swing.GroupLayout jpRoleLayout = new javax.swing.GroupLayout(jpRole);
        jpRole.setLayout(jpRoleLayout);
        jpRoleLayout.setHorizontalGroup(
            jpRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRoleLayout.createSequentialGroup()
                .addComponent(lblIconRole, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jpRoleLayout.setVerticalGroup(
            jpRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRoleLayout.createSequentialGroup()
                .addComponent(lblIconRole, javax.swing.GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jpnHomeScreenLayout = new javax.swing.GroupLayout(jpnHomeScreen);
        jpnHomeScreen.setLayout(jpnHomeScreenLayout);
        jpnHomeScreenLayout.setHorizontalGroup(
            jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jpnQuanLyNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHomeScreenLayout.createSequentialGroup()
                                .addComponent(jpnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jpnQuanLySanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHomeScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpnInfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnDayAndTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(10, 10, 10))
        );
        jpnHomeScreenLayout.setVerticalGroup(
            jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                        .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                                .addComponent(jpnDayAndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(jpnHomeScreenLayout.createSequentialGroup()
                        .addComponent(jpnInfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jpRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jpnHomeScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnQuanLyNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQuanLySanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jpnQuanLyNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnQuanLyNhanVienMouseClicked
        EmployeeManagementView employeeM = new EmployeeManagementView();
        employeeM.setVisible(true);
        employeeM.setLocationRelativeTo(null);
        setVisible(false);
        System.out.println("You have just pressed the 'Quan Ly Nhan Vien' ");
    }//GEN-LAST:event_jpnQuanLyNhanVienMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Thoát Chương Trình", "Thoát", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMouseClicked

    private void jpnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnHoaDonMouseClicked
        // TODO add your handling code here:
        BillDetailView invoice = new BillDetailView();
        invoice.setVisible(true);
        invoice.setLocationRelativeTo(null);
        setVisible(false);
        System.out.println("You have just pressed button 'Hoa Don' ");
    }//GEN-LAST:event_jpnHoaDonMouseClicked

    private void jpnQuanLySanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnQuanLySanPhamMouseClicked
        // TODO add your handling code here:
        ProductManagementView productM = new ProductManagementView();
        productM.setVisible(true);
        productM.setLocationRelativeTo(null);
        setVisible(false);
    }//GEN-LAST:event_jpnQuanLySanPhamMouseClicked

    private void jpRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpRoleMouseClicked
        // TODO add your handling code here:
        RoleUserView roleUser = new RoleUserView();
        roleUser.setVisible(true);
        roleUser.setLocationRelativeTo(null);
        setVisible(false);
    }//GEN-LAST:event_jpRoleMouseClicked

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Đăng Xuất Khỏi Hệ Thống", "Đăng Xuất", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            LoginView login = new LoginView();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            setVisible(false);
            this.pack();
        }

    }//GEN-LAST:event_btnDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnExit;
    private javax.swing.JPanel jpRole;
    private javax.swing.JPanel jpnDayAndTime;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnHomeScreen;
    private javax.swing.JPanel jpnInfoUser;
    private javax.swing.JPanel jpnQuanLyNhanVien;
    private javax.swing.JPanel jpnQuanLySanPham;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblHomeScreen;
    private javax.swing.JLabel lblIconInfoUser;
    private javax.swing.JLabel lblIconQLNV;
    private javax.swing.JLabel lblIconRole;
    private javax.swing.JLabel lblIconSell;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblQLNV;
    private javax.swing.JLabel lblQuanLyKho;
    private javax.swing.JLabel lblRoleName;
    private javax.swing.JLabel lblSell;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimme;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lbliconQLK;
    // End of variables declaration//GEN-END:variables
}
