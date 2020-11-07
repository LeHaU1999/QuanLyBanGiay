/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.sma.controller.ReportController;
import com.sma.dto.ReportBillDto;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ASUS
 */
public class ReportView extends javax.swing.JFrame {

    public ReportView() {
        initComponents();
        this.setLocationRelativeTo(null);
        showDay();

        loadDataDateToComboBox();
        loadDataEmpToComboBox();
        editOption();

        loadDataToTableByDay();
        txtTongDoanhThu.setText("1000");
        txtSLHoaDon.setText("50");

        txtNgay.setEditable(false);
        txtSLHoaDon.setEditable(false);
        txtTongDoanhThu.setEditable(false);
        cboDateReport.setSelectedItem(null);
        cboMaNhanVienReport.setSelectedItem(null);
    }

    ArrayList<String> listComboBoxDate = ReportController.loadDataDateToComboBox();
    ArrayList<String> listComboBoxEmp = ReportController.loadDataEmpToComboBox();

    public void loadDataDateToComboBox() {
        for (String item : listComboBoxDate) {
            cboDateReport.addItem(item.toString());
        }
    }

    public void loadDataEmpToComboBox() {
        for (String item : listComboBoxEmp) {
            cboMaNhanVienReport.addItem(item.toString());
        }
    }

    void showDay() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        txtNgay.setText(dateFormat.format(date));
    }

    void editOption() {
        if (jckChooseDate.isSelected()) {
            cboDateReport.setEnabled(true);
        } else {
            cboDateReport.setEnabled(false);
        }

        if (jckChooseIdEmp.isSelected()) {
            cboMaNhanVienReport.setEnabled(true);
        } else {
            cboMaNhanVienReport.setEnabled(false);
        }
    }

    private void loadDataToTableByDay() {
        try {
            List<ReportBillDto> list = ReportController.getDataByDay(txtNgay.getText().trim());
            DefaultTableModel model = (DefaultTableModel) tblHoaDonReport.getModel();
            Object[] object = new Object[4];
            for (int i = 0; i < list.size(); i++) {
                object[0] = list.get(i).getDayBill();
                object[1] = list.get(i).getId_emp();
                object[2] = list.get(i).getId_bill();
                object[3] = list.get(i).getTotalMoney();
                model.addRow(object);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn được tạo ");
        }
    }

    private void loadDataToTableByEmp() {
        try {
            List<ReportBillDto> list = ReportController.getDataByEmp(cboMaNhanVienReport.getSelectedItem().toString().trim());
            DefaultTableModel model = (DefaultTableModel) tblHoaDonReport.getModel();
            Object[] object = new Object[4];
            for (int i = 0; i < list.size(); i++) {
                object[0] = list.get(i).getDayBill();
                object[1] = list.get(i).getId_emp();
                object[2] = list.get(i).getId_bill();
                object[3] = list.get(i).getTotalMoney();
                model.addRow(object);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn được tạo ");
        }
    }

    private void loadDataToTableByDayAndEmp() {
        try {
            String day = cboDateReport.getSelectedItem().toString().trim();
            String emp = cboMaNhanVienReport.getSelectedItem().toString().trim();

            List<ReportBillDto> list = ReportController.getDataByDayAndEmp(day, emp);
            DefaultTableModel model = (DefaultTableModel) tblHoaDonReport.getModel();
            Object[] object = new Object[4];
            for (int i = 0; i < list.size(); i++) {
                object[0] = list.get(i).getDayBill();
                object[1] = list.get(i).getId_emp();
                object[2] = list.get(i).getId_bill();
                object[3] = list.get(i).getTotalMoney();
                model.addRow(object);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn được tạo ");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnInfoReport = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonReport = new javax.swing.JTable();
        cboDateReport = new javax.swing.JComboBox();
        cboMaNhanVienReport = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSLHoaDon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        jckChooseDate = new javax.swing.JCheckBox();
        jckChooseIdEmp = new javax.swing.JCheckBox();
        btnSearch = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnTroLai = new javax.swing.JButton();
        jpnBarChart = new javax.swing.JPanel();
        btnShowChart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BÁO CÁO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10))
        );

        jpnInfoReport.setBackground(new java.awt.Color(255, 255, 255));
        jpnInfoReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ngày: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mã Nhân Viên:");

        tblHoaDonReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Nhân Viên", "Mã Hóa Đơn", "Giá Trị Hóa Đơn"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonReport);

        cboDateReport.setMaximumRowCount(100);
        cboDateReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboDateReportMouseClicked(evt);
            }
        });

        cboMaNhanVienReport.setMaximumRowCount(50);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Tổng Doanh Thu");

        jLabel5.setText("Số Hóa Đơn:");

        jLabel6.setText("Ngày:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongDoanhThu)
                    .addComponent(txtSLHoaDon)
                    .addComponent(txtNgay))
                .addGap(73, 73, 73))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSLHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jckChooseDate.setText("Choose");
        jckChooseDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jckChooseDateMouseClicked(evt);
            }
        });
        jckChooseDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckChooseDateActionPerformed(evt);
            }
        });

        jckChooseIdEmp.setText("Choose");
        jckChooseIdEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jckChooseIdEmpMouseClicked(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnTroLai.setText("X");
        btnTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnInfoReportLayout = new javax.swing.GroupLayout(jpnInfoReport);
        jpnInfoReport.setLayout(jpnInfoReportLayout);
        jpnInfoReportLayout.setHorizontalGroup(
            jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnInfoReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpnInfoReportLayout.createSequentialGroup()
                        .addComponent(btnTroLai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnInfoReportLayout.createSequentialGroup()
                        .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboMaNhanVienReport, 0, 152, Short.MAX_VALUE)
                            .addComponent(cboDateReport, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jckChooseDate)
                    .addComponent(jckChooseIdEmp)
                    .addComponent(btnClose))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jpnInfoReportLayout.setVerticalGroup(
            jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoReportLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboDateReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jckChooseDate))
                .addGap(18, 18, 18)
                .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboMaNhanVienReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jckChooseIdEmp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnInfoReportLayout.createSequentialGroup()
                        .addGroup(jpnInfoReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSearch)
                            .addComponent(btnClose))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnInfoReportLayout.createSequentialGroup()
                        .addComponent(btnTroLai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jpnBarChart.setBackground(new java.awt.Color(255, 255, 255));
        jpnBarChart.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "report"));
        jpnBarChart.setLayout(new javax.swing.BoxLayout(jpnBarChart, javax.swing.BoxLayout.LINE_AXIS));

        btnShowChart.setText("show");
        btnShowChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpnInfoReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnShowChart)
                .addGap(465, 465, 465))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnInfoReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jpnBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowChart))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String day = (String) cboDateReport.getSelectedItem();
        txtNgay.setText(day);
        DefaultTableModel model = (DefaultTableModel) tblHoaDonReport.getModel();
        if (jckChooseDate.isSelected() && jckChooseIdEmp.isSelected()) {
            model.setRowCount(0);
            loadDataToTableByDayAndEmp();
        } else if (jckChooseDate.isSelected()) {
            model.setRowCount(0);
            loadDataToTableByDay();
        } else if (jckChooseIdEmp.isSelected()) {
            model.setRowCount(0);
            loadDataToTableByEmp();
        } else {
            showDay();
        }

        // int index =tblHoaDonReport.getSelectedRow();
        double tongTien = 0;
        int col = 0;
        for (int i = 0; i < tblHoaDonReport.getRowCount(); i++) {
            double donGia = (double) tblHoaDonReport.getValueAt(i, 3);
            tongTien += donGia;
            col = i + 1;
        }
        txtTongDoanhThu.setText(String.valueOf(tongTien));
        txtSLHoaDon.setText(String.valueOf(col));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jckChooseDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jckChooseDateMouseClicked
        if (jckChooseDate.isSelected()) {
            cboDateReport.setEnabled(true);

        } else {
            cboDateReport.setEnabled(false);
        }
    }//GEN-LAST:event_jckChooseDateMouseClicked

    private void jckChooseIdEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jckChooseIdEmpMouseClicked
        // TODO add your handling code here:
        if (jckChooseIdEmp.isSelected()) {
            cboMaNhanVienReport.setEnabled(true);

        } else {
            cboMaNhanVienReport.setEnabled(false);
        }
    }//GEN-LAST:event_jckChooseIdEmpMouseClicked

    private void cboDateReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDateReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDateReportMouseClicked

    private void jckChooseDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckChooseDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jckChooseDateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        HomeScreen home = new HomeScreen();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnTroLaiActionPerformed

    private void btnShowChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowChartActionPerformed
        // TODO add your handling code here:
        int row = tblHoaDonReport.getRowCount();
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();

        double sl = Double.parseDouble(txtSLHoaDon.getText());
        for (int i = 0; i < row; i++) {
            double tomg = (double) tblHoaDonReport.getValueAt(i, 3);
            String val = (String) tblHoaDonReport.getValueAt(i, 2);
            
            dcd.setValue(tomg, val, "Tổng GT Đơn Hàng");
        }
        JFreeChart jfrChart = ChartFactory.createBarChart("GT Tùng Hóa Đơn Trong Ngày", "2", null, dcd, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = jfrChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLUE);

        ChartFrame chartFrm = new ChartFrame("567", jfrChart, true);
        chartFrm.setVisible(true);
        chartFrm.setSize(50, 50);

        ChartPanel chartPan = new ChartPanel(jfrChart);

        jpnBarChart.removeAll();
        jpnBarChart.add(chartPan);
        jpnBarChart.updateUI();

    }//GEN-LAST:event_btnShowChartActionPerformed

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
            java.util.logging.Logger.getLogger(ReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowChart;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JComboBox cboDateReport;
    private javax.swing.JComboBox cboMaNhanVienReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jckChooseDate;
    private javax.swing.JCheckBox jckChooseIdEmp;
    private javax.swing.JPanel jpnBarChart;
    private javax.swing.JPanel jpnInfoReport;
    private javax.swing.JTable tblHoaDonReport;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSLHoaDon;
    private javax.swing.JTextField txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
