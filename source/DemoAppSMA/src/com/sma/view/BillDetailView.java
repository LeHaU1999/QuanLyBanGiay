/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.sma.controller.BillDetailController;
import com.sma.entity.Bill;
import com.sma.entity.BillDetail;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class BillDetailView extends javax.swing.JFrame {

    /**
     * Creates new form InvoiceView
     */
    public BillDetailView() {
        initComponents();
        loadDataToTableInvoice();
        optionEnable();
        this.setLocationRelativeTo(null);
        btnPrintDetail.setEnabled(false);
    }

    public void loadDataToTableInvoice() {
        List<Bill> listInvoice = BillDetailController.getAllInvoice();
        DefaultTableModel model = (DefaultTableModel) tblListInvoice.getModel();
        Object[] object = new Object[7];
        for (int i = 0; i < listInvoice.size(); i++) {
            object[1] = listInvoice.get(i).getId();
            object[2] = listInvoice.get(i).getId_employee();
            object[3] = listInvoice.get(i).getId_customer();
            object[4] = listInvoice.get(i).getNameCustomer();
            object[5] = listInvoice.get(i).getSdt();
            object[6] = listInvoice.get(i).getTotalMoney();
            object[0] = listInvoice.get(i).getDayBill();
            model.addRow(object);
        }
    }

    public void optionEnable() {
        txtIdKH.setEditable(false);
        txtIdNV.setEditable(false);
        txtMaHD.setEditable(false);
        txtMaHD_detail.setEditable(false);
        txtNameKH.setEditable(false);
        txtSdt.setEditable(false);
        txtTotal.setEditable(false);
        txtTotal_detail.setEditable(false);

        txtIdKH.setText("");
        txtIdNV.setText("");
        txtMaHD.setText("");
        txtMaHD_detail.setText("");
        txtNameKH.setText("");
        txtSdt.setText("");
        txtTotal.setText("");
        txtTotal_detail.setText("");

        btnDeleteBill.setEnabled(false);
        btnDetail.setEnabled(false);
    }

    public void loadDataToTableByFindBill() {
        List<Bill> listSearchBill = BillDetailController.getBillByInfo(txtSearch.getText());
        DefaultTableModel model = (DefaultTableModel) tblListInvoice.getModel();
        int a = 0;
        Object[] object = new Object[7];
        for (int i = 0; i < listSearchBill.size(); i++) {
            object[0] = listSearchBill.get(i).getDayBill();
            object[1] = listSearchBill.get(i).getId();
            object[2] = listSearchBill.get(i).getId_customer();
            object[3] = listSearchBill.get(i).getNameCustomer();
            object[4] = listSearchBill.get(i).getSdt();
            object[5] = listSearchBill.get(i).getId_employee();
            object[6] = listSearchBill.get(i).getTotalMoney();
            model.addRow(object);
        }
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đơn hàng với từ khóa: [ " + txtSearch + " ]");
        }
    }

    public void Print_Bill() {

        File file = new File("danhsachhoadon.txt");
        file.delete();
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("danhsachhoadon.txt"), "UTF8"));
            b.write("\t\t\t\tDANH SACH HOA DON\n");
            b.write("\t\t\t\t****************\n");
            b.write("\n");
            b.write("\n------------------------------------------------------------------------------------------------------\n");

            b.write("  "+"STT"+"\t\t " + "[Day]" + "\t\t" + "[Ma HĐ]" + "\t" + "[Mã NV]" + "\t" + "[Mã KH]" + "\t" + "[Tên KH]" + "\t" + "[SĐT KH]" + "\t" + "Tổng Đơn Hàng");

            double tongTienTatCaHoaDon = 0;
            int index = tblListInvoice.getRowCount();
            for (int i = 0; i < index; i++) {
                String a0 = String.valueOf(i+1);
                String a1 = (String) tblListInvoice.getValueAt(i, 0);
                String a2 = (String) tblListInvoice.getValueAt(i, 1);
                String a3 = (String) tblListInvoice.getValueAt(i, 2);
                String a4 = (String) tblListInvoice.getValueAt(i, 3);
                String a5 = (String) tblListInvoice.getValueAt(i, 4);
                String a6 = (String) tblListInvoice.getValueAt(i, 5);

                double a7 = (double) tblListInvoice.getValueAt(0, 6);
                String aa7 = String.valueOf(a7);
                tongTienTatCaHoaDon += a7;

                String s0 = a0;
                String s1 = a1;
                String s2 = a2;
                String s3 = a3;
                String s4 = a4;
                String s5 = a5;
                String s6 = a6;
                String s7 = aa7;

                b.write("\n------------------------------------------------------------------------------------------------------\n");
                b.write("   "+s0+"\t  " + s1 + "\t\t" + s2 +"\t" + s3 + "\t" + s4 + "\t" + s5 + "\t" + s6 + "\t" + s7);
            }
            b.write("\n------------------------------------------------------------------------------------------------------\n");
            b.write("\n " + "                                                                        =>TỔNG GIÁ TRỊ: " + tongTienTatCaHoaDon+ "\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  danhsachhoadon.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi cmnr");
        }

    }

    public void Print_Bill_Detail() {
        String mahd = txtMaHD_detail.getText();
        File file = new File("chitiethoadon"+mahd+".txt");
        file.delete();
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("chitiethoadon"+mahd+".txt"), "UTF8"));
            b.write("\t\t\tCHI TIẾTHÓA ĐƠN\n");
            b.write("\t\t\t****************\n");
            b.write("\n");
            b.write("\tMã HĐ: " + txtMaHD_detail.getText() + " \n");
            b.write("\tTotal: " + txtTotal_detail.getText() +  " \n");
            b.write("\n-------------------------------------------------------------------\n");
            b.write("  "+"STT"+"\t " + "[Ma Sp]" + "\t" + "[Type]" + "\t\t" + "[Size]" + "\t" + "[SL]" + "\t" + "[Đơn Giá]");

            int index = tblChiTietHoaDon.getRowCount();
            for (int i = 0; i < index; i++) {
                String a0 = String.valueOf(i+1);
                String a1 = (String) tblChiTietHoaDon.getValueAt(i, 0);
                String a2 = (String) tblChiTietHoaDon.getValueAt(i, 1);
                int a3 = (int) tblChiTietHoaDon.getValueAt(i, 2);
                String aa3 = String.valueOf(a3);
                
                 int a4 = (int) tblChiTietHoaDon.getValueAt(i, 3);
                String aa4 = String.valueOf(a4);
                
                double a5 = (double) tblChiTietHoaDon.getValueAt(i, 4);
                String aa5 = String.valueOf(a5);

                String s0 = a0;
                String s1 = a1;
                String s2 = a2;
                String s3 = aa3;
                String s4 = aa4;
                String s5 = aa5;
               
                b.write("\n-------------------------------------------------------------------\n");
                b.write("  "+s0+ "\t " + s1 + "\t" + s2 + "\t" + s3 + "\t" + s4 + "\t" + s5 );
            }
            b.write("\n-------------------------------------------------------------------\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  chitiethoadon"+mahd+".txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi cmnr");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtIdNV = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtIdKH = new javax.swing.JTextField();
        txtNameKH = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        btnDetail = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListInvoice = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnDeleteBill = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnInHoaDon = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnGoToReport = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD_detail = new javax.swing.JTextField();
        txtTotal_detail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnPrintDetail = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Mã HD:");

        jLabel2.setText("Id_NV");

        jLabel3.setText("Total:");

        jLabel4.setText("Id_KH");

        jLabel5.setText("Tên_KH");

        jLabel6.setText("SĐT:");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnDetail.setText("CHI TIẾT");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        tblListInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Mã HD", "Nhân Viên", "Khách Hàng", "Tên KH", "SĐT", "Total"
            }
        ));
        tblListInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListInvoiceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListInvoice);

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 0, 51));
        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnDeleteBill.setText("DELETE");
        btnDeleteBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBillActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DANH SÁCH HÓA ĐƠN");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSearch.setText("Search...");
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-print.png"))); // NOI18N
        btnInHoaDon.setText("Print");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(153, 153, 153));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnGoToReport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGoToReport.setForeground(new java.awt.Color(0, 51, 255));
        btnGoToReport.setText("Go To Report ");
        btnGoToReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoToReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdNV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdKH)
                            .addComponent(txtNameKH)
                            .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGoToReport)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetail))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtIdNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteBill))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnInHoaDon)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoToReport)
                    .addComponent(btnClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Product", "Type", "Size", "SL", "Đơn Giá"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        jLabel7.setText("Mã HD:");

        txtTotal_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotal_detailActionPerformed(evt);
            }
        });

        jLabel8.setText("Total:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CHI TIẾT HÓA ĐƠN");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPrintDetail.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        btnPrintDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-print.png"))); // NOI18N
        btnPrintDetail.setText("Print");
        btnPrintDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal_detail)
                    .addComponent(txtMaHD_detail, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrintDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaHD_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintDetail))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotal_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void tblListInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListInvoiceMouseClicked
        // TODO add your handling code here:
        int index = tblListInvoice.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblListInvoice.getModel();
        txtMaHD.setText(model.getValueAt(index, 1).toString());
        txtIdNV.setText(model.getValueAt(index, 2).toString());
        txtIdKH.setText(model.getValueAt(index, 3).toString());
        txtNameKH.setText(model.getValueAt(index, 4).toString());
        txtSdt.setText(model.getValueAt(index, 5).toString());
        txtTotal.setText(model.getValueAt(index, 6).toString());

        btnDeleteBill.setEnabled(true);
        btnDetail.setEnabled(true);
    }//GEN-LAST:event_tblListInvoiceMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        HomeScreen home = new HomeScreen();
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:

        List<BillDetail> listBill_Detail = BillDetailController.getAllBill_Detail(txtMaHD.getText());
        txtMaHD_detail.setText(txtMaHD.getText());
        txtTotal_detail.setText(txtTotal.getText());
        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        model.setRowCount(0);
        Object[] object = new Object[5];
        for (int i = 0; i < listBill_Detail.size(); i++) {
            object[0] = listBill_Detail.get(i).getId_product();
            object[1] = listBill_Detail.get(i).getType();
            object[2] = listBill_Detail.get(i).getSize();
            object[3] = listBill_Detail.get(i).getCount();
            object[4] = listBill_Detail.get(i).getDonGia();
            model.addRow(object);
        }
        btnPrintDetail.setEnabled(true);
    }//GEN-LAST:event_btnDetailActionPerformed

    private void txtTotal_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotal_detailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotal_detailActionPerformed

    private void btnDeleteBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBillActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(null, "Are You Sure", "DELETE", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                String id_bill = txtMaHD.getText();
                BillDetailController.deleteBill_Detail(id_bill);
                DefaultTableModel model_Bill_Detail = (DefaultTableModel) tblChiTietHoaDon.getModel();
                model_Bill_Detail.setRowCount(0);
                try {
                    BillDetailController.deleteInvoice(id_bill);
                    DefaultTableModel model_Bill = (DefaultTableModel) tblListInvoice.getModel();
                    model_Bill.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Đã Xóa Dơn Hàng " + id_bill);
                    optionEnable();
                    loadDataToTableInvoice();
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnDeleteBillActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tblListInvoice.getModel();
            model.setRowCount(0);
            loadDataToTableByFindBill();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đơn hàng với từ khóa: [ " + txtSearch + " ]");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblListInvoice.getModel();
        model.setRowCount(0);
        txtSearch.setText("");
        loadDataToTableInvoice();
    }//GEN-LAST:event_txtSearchMouseClicked

    private void btnGoToReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoToReportActionPerformed
        // TODO add your handling code here:
        ReportView rv = new ReportView();
        rv.setVisible(true);
    }//GEN-LAST:event_btnGoToReportActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:

       Print_Bill();

        
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnPrintDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDetailActionPerformed
        // TODO add your handling code here:
        Print_Bill_Detail();
    }//GEN-LAST:event_btnPrintDetailActionPerformed

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
            java.util.logging.Logger.getLogger(BillDetailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillDetailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillDetailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillDetailView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillDetailView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDeleteBill;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnGoToReport;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnPrintDetail;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblListInvoice;
    private javax.swing.JTextField txtIdKH;
    private javax.swing.JTextField txtIdNV;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHD_detail;
    private javax.swing.JTextField txtNameKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal_detail;
    // End of variables declaration//GEN-END:variables
}
