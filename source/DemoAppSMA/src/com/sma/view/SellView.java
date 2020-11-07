/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.sma.config.JDBCConnection;
import com.sma.controller.RoleController;
import com.sma.controller.SellController;
import com.sma.dto.SearchProductDto;
import com.sma.entity.Bill;
import com.sma.entity.BillDetail;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class SellView extends javax.swing.JFrame {

    //private Vector size;
    public SellView() {
        initComponents();
        showInfoUser();
        showDay();
        showTime();
        getIdEmpfromLogin();
        this.setLocationRelativeTo(null);
        buttonOption();
    }
    LoginView loginView = new LoginView();

    public void showInfoUser() {
        //LoginView loginView = new LoginView();
        lblUser.setText(loginView.userName);
        lblRoleName.setText(loginView.roleName);
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
                lblTime.setText(dateFormat.format(date));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).start();
    }

    public void getIdEmpfromLogin() {
        LoginView loginView = new LoginView();
        lblUser.setText("Username: " + loginView.userName);
        lblRoleName.setText("Role: " + loginView.roleName);
        lblId.setText("ID: " + loginView.id_emp);
    }

    public void autoIdHoaDon() {
        Connection conn = null;
        try {
            conn = JDBCConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Max(id_bill) FROM  bills");
            rs.next();
            if (rs.getString("Max(id_bill)") == null) {
                txtMaHoaDon.setText("HD001");
            } else {
                int id = Integer.parseInt(rs.getString("Max(id_bill)").substring(4, rs.getString("Max(id_bill)").length()));
                id++;
                txtMaHoaDon.setText("HD00" + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PrintHoaDon() {
        String mahd = txtMaHoaDon.getText();
        File file = new File("hoadon"+mahd+".txt");
        file.delete();
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("hoadon"+mahd+".txt"), "UTF8"));
            b.write("\t\t\t\tHÓA ĐƠN BÁN HÀNG\n");
            b.write("\t\t\t\t****************\n");
            b.write("\n");
            b.write("\t\tDay: " + lblDate.getText() + "\t\tID_bill: " + txtMaHoaDon.getText() + " \n");
            b.write("\t\tTime: " + lblTime.getText() + "\t\tID_Cus: " + txtMaKhachHang.getText() + " \n");
            b.write("\t\tID_emp: " + loginView.id_emp + " \t\t\tSĐT: " + txtSDT.getText() + " \n");
            b.write("\n--------------------------------------------------------------------------------\n");

            b.write("   " + "[- Mã SP -]" + "\t" + "[- TÊN SẢN PHẨM -]" + "\t" + "ĐVT" + "\t" + "SIZE" + "\t" + "SL" + "\t" + "ĐG" + "\t" + "TOTAL");

            int index = tblSell.getRowCount();
            for (int i = 0; i < index; i++) {

                String a1 = (String) tblSell.getValueAt(i, 0);
                String a2 = (String) tblSell.getValueAt(i, 1);
                //String a3 = (String) tblSell.getValueAt(i, 2);
                String a4 = (String) tblSell.getValueAt(i, 3);

                int a5 = (int) tblSell.getValueAt(0, 4);
                String aa5 = String.valueOf(a5);

                String a6 = (String) tblSell.getValueAt(i, 5);

                double a7 = (double) tblSell.getValueAt(0, 6);
                String aa7 = String.valueOf(a7);

                String a8 = (String) tblSell.getValueAt(i, 7);

                String s1 = a1;
                String s2 = a2;
                //String s3 = a3;
                String s4 = a4;
                String s5 = (String) aa5;
                String s6 = a6;
                String s7 = aa7;
                String s8 = a8;
                b.write("\n--------------------------------------------------------------------------------\n");
                b.write("   " + s1 + "\t" + s2 + "\t" + s4 + "\t" + s5 + "\t" + s6 + "\t" + s7 + "\t" + s8);
            }
            b.write("\n--------------------------------------------------------------------------------\n");
            b.write("\n " + "                                               =>    TỔNG THANH TOÁN: " + txtTongTien.getText() + "\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  hoadon"+mahd+".txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "loi cmnr");
        }
    }

    public void autoIdKhachHang() {
        Connection conn = null;
        try {
            conn = JDBCConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Max(id_customer) FROM  bills");
            rs.next();
            if (rs.getString("Max(id_customer)") == null) {
                txtMaKhachHang.setText("KH0001");
            } else {
                int id = Integer.parseInt(rs.getString("Max(id_customer)").substring(5, rs.getString("Max(id_customer)").length()));
                id++;
                txtMaKhachHang.setText("KH000" + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDataToTableByFindProduct() {
        List<SearchProductDto> listSearchProduct = SellController.getProductByName(txtSearchProduct.getText());
        DefaultTableModel model = (DefaultTableModel) tblSearchProduct.getModel();
        int a = 0;
        Object[] object = new Object[7];
        for (int i = 0; i < listSearchProduct.size(); i++) {
            object[0] = listSearchProduct.get(i).getId();
            object[1] = listSearchProduct.get(i).getName();
            object[2] = listSearchProduct.get(i).getType();
            object[3] = listSearchProduct.get(i).getUnit();
            object[4] = listSearchProduct.get(i).getSize();
            object[5] = listSearchProduct.get(i).getCount();
            object[6] = listSearchProduct.get(i).getMoneyOutput();
            model.addRow(object);
        }
    }

    public void totalProduct() {
        int sizeChoose = Integer.parseInt((String) cboSoLuong.getSelectedItem());
        double donGia = Double.parseDouble(txtDonGia.getText());
        Double totalProduct = sizeChoose * donGia;
        txtTotalProduct.setText(String.valueOf(totalProduct));
    }

    public void addBill() {

        String dayBill = lblDate.getText();
        String id_bill = txtMaHoaDon.getText();
        String id_Cus = txtMaKhachHang.getText();
        String TenKH = txtTenKH.getText();
        String sdt = txtSDT.getText();
        String id_emp = loginView.id_emp;
        try {
            if (id_bill.equals("")) {
                JOptionPane.showMessageDialog(null, "Mã Hóa Dơn Trống, Vui lòng xem lại Hóa Đơn");
            } else if (id_Cus.equals("")) {
                JOptionPane.showMessageDialog(null, "Không Có Mã Khách Hàng, Vui lòng xem lại Hóa Đơn");
            } else if (TenKH.equals("")) {
                TenKH = "null";
                System.out.println(TenKH);
            } else if (sdt.equals("")) {
                sdt = "null";
                System.out.println(sdt);
            } else if (id_emp.equals("")) {
                JOptionPane.showMessageDialog(null, "Không Có Mã Nhân Viên, Vui lòng xem lại");
            } else {
                try {
                    Bill bill = new Bill();
                    bill.setDayBill(dayBill);
                    bill.setId(id_bill);
                    bill.setId_customer(id_Cus);
                    bill.setNameCustomer(TenKH);
                    bill.setSdt(sdt);
                    bill.setId_employee(id_emp);
                    bill.setTotalMoney(Double.parseDouble(txtTongTien.getText()));
                    SellController.addBill(bill);
                    JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Thanh toán thất bại |");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thanh Toán Thất Bại ||");
        }
    }

    public void addBill_detail() {
        try {

            for (int i = 0; i < tblSell.getRowCount(); i++) {
                String id_bill = txtMaHoaDon.getText();
                String id_product = (String) tblSell.getValueAt(i, 0);
                String type = (String) tblSell.getValueAt(i, 2);
                Object size = tblSell.getValueAt(i, 4);
                int size2 = (int) size;
                String count = (String) tblSell.getValueAt(i, 5);
                int count2 = Integer.parseInt(count);
                double donGia = (double) tblSell.getValueAt(i, 6);
                BillDetail bill_detail = new BillDetail();
                bill_detail.setId_bill(id_bill);
                bill_detail.setId_product(id_product);
                bill_detail.setType(type);
                bill_detail.setSize(size2);
                bill_detail.setCount(count2);
                bill_detail.setDonGia(donGia);
                SellController.addBill_detail(bill_detail);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thanh Toán Thất Bại error: detail |||");
        }
    }

    void buttonOption() {
        btnAddProductInBag.setEnabled(false);
        btnDelete.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnInHoaDon.setEnabled(false);
        btnSearch.setEnabled(false);
        btnTinhToan.setEnabled(false);

        txtMaHoaDon.setEditable(false);
        txtMaKhachHang.setEditable(false);
        txtTenSP.setEditable(false);
        txtDonGia.setEditable(false);
        txtTienThoiLai.setEditable(false);
        txtTongTien.setEditable(false);
        txtTotalProduct.setEditable(false);
        cboSoLuong.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpnInfoEmployees = new javax.swing.JPanel();
        lblIconInfoUser = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblRoleName = new javax.swing.JLabel();
        lblIconTime = new javax.swing.JLabel();
        lblIconDay = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jpnInfoSale = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblTienKhachDua = new javax.swing.JLabel();
        lblTienThoiLai = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienThoiLai = new javax.swing.JTextField();
        btnTinhToan = new javax.swing.JButton();
        jpnSearchProduct = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearchProduct = new javax.swing.JTable();
        jpnSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtSearchProduct = new javax.swing.JTextField();
        btnAddProductInBag = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        cboSoLuong = new javax.swing.JComboBox();
        txtTotalProduct = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jpnDetailInfoSale = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSell = new javax.swing.JTable();
        txtTongTien = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jpnInfoEmployees.setBackground(new java.awt.Color(255, 255, 255));
        jpnInfoEmployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIconInfoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconInfoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-user.png"))); // NOI18N
        lblIconInfoUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 76, 128)));

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 76, 128));
        lblUser.setText("User: xxx");

        lblRoleName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRoleName.setForeground(new java.awt.Color(238, 28, 37));
        lblRoleName.setText("Role: ???");

        lblIconTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-clock.png"))); // NOI18N

        lblIconDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-calendar.png"))); // NOI18N

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lblId.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lblId.setText("ID: ???");

        javax.swing.GroupLayout jpnInfoEmployeesLayout = new javax.swing.GroupLayout(jpnInfoEmployees);
        jpnInfoEmployees.setLayout(jpnInfoEmployeesLayout);
        jpnInfoEmployeesLayout.setHorizontalGroup(
            jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                        .addComponent(lblIconInfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRoleName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                        .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                                .addComponent(lblIconDay)
                                .addGap(18, 18, 18)
                                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                                .addComponent(lblIconTime)
                                .addGap(18, 18, 18)
                                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnInfoEmployeesLayout.setVerticalGroup(
            jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblIconInfoUser)
                    .addGroup(jpnInfoEmployeesLayout.createSequentialGroup()
                        .addComponent(lblId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRoleName, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnInfoEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jpnInfoSale.setBackground(new java.awt.Color(255, 255, 255));
        jpnInfoSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaHD.setText("Mã HD:");

        lblMaKH.setText("Mã KH:");

        lblTenKH.setText("Tên KH:");

        lblSDT.setText("SĐT:");

        lblTienKhachDua.setText("Tiền Khách Đưa:");

        lblTienThoiLai.setText("Tiền Thối Lại:");

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        btnTinhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-money.png"))); // NOI18N
        btnTinhToan.setText("Tính");
        btnTinhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnInfoSaleLayout = new javax.swing.GroupLayout(jpnInfoSale);
        jpnInfoSale.setLayout(jpnInfoSaleLayout);
        jpnInfoSaleLayout.setHorizontalGroup(
            jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                        .addComponent(lblMaHD)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                        .addComponent(lblMaKH)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaKhachHang))
                    .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                        .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenKH)
                            .addComponent(lblSDT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDT))))
                .addGap(51, 51, 51)
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienKhachDua)
                    .addComponent(lblTienThoiLai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                        .addComponent(txtTienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTinhToan))
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        jpnInfoSaleLayout.setVerticalGroup(
            jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHD)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKH)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienKhachDua)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThoiLai)
                    .addComponent(txtTienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTinhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSearchProduct.setBackground(new java.awt.Color(255, 255, 255));
        jpnSearchProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSearchProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblSearchProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Type", "Đơn Vị", "Size", "Số Lượng", "Đơn Giá"
            }
        ));
        tblSearchProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSearchProduct);
        if (tblSearchProduct.getColumnModel().getColumnCount() > 0) {
            tblSearchProduct.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblSearchProduct.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblSearchProduct.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblSearchProduct.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblSearchProduct.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jpnSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)), "Tìm Kiếm Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSearchProduct.setText("Search...");
        txtSearchProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchProductMouseClicked(evt);
            }
        });
        txtSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnSearchLayout = new javax.swing.GroupLayout(jpnSearch);
        jpnSearch.setLayout(jpnSearchLayout);
        jpnSearchLayout.setHorizontalGroup(
            jpnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnSearchLayout.setVerticalGroup(
            jpnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddProductInBag.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddProductInBag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-add-product-in-box.png"))); // NOI18N
        btnAddProductInBag.setText("ADD");
        btnAddProductInBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductInBagActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên Sản Phẩm: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("X");

        cboSoLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboSoLuong.setMaximumRowCount(15);
        cboSoLuong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cboSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSoLuongMouseClicked(evt);
            }
        });
        cboSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSoLuongActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("=");

        javax.swing.GroupLayout jpnSearchProductLayout = new javax.swing.GroupLayout(jpnSearchProduct);
        jpnSearchProduct.setLayout(jpnSearchProductLayout);
        jpnSearchProductLayout.setHorizontalGroup(
            jpnSearchProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnSearchProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnSearchProductLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalProduct))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(btnAddProductInBag, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jpnSearchProductLayout.setVerticalGroup(
            jpnSearchProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnSearchProductLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnSearchProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotalProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddProductInBag)
                            .addComponent(cboSoLuong))))
                .addGap(6, 6, 6))
        );

        jpnDetailInfoSale.setBackground(new java.awt.Color(255, 255, 255));
        jpnDetailInfoSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Type", "Đơn Vị Tính", "Size", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        tblSell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSellMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSell);

        txtTongTien.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 51, 51));
        txtTongTien.setText("???");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setText("Tổng Tiền:");

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-pay.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-remove.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-print.png"))); // NOI18N
        btnInHoaDon.setText("SAVE");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 0, 51));
        btnClose.setText("CLOSE");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnDetailInfoSaleLayout = new javax.swing.GroupLayout(jpnDetailInfoSale);
        jpnDetailInfoSale.setLayout(jpnDetailInfoSaleLayout);
        jpnDetailInfoSaleLayout.setHorizontalGroup(
            jpnDetailInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInfoSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan)
                .addGap(26, 26, 26)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnDetailInfoSaleLayout.setVerticalGroup(
            jpnDetailInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDetailInfoSaleLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnDetailInfoSaleLayout.createSequentialGroup()
                        .addGroup(jpnDetailInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInfoSaleLayout.createSequentialGroup()
                        .addGroup(jpnDetailInfoSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpnInfoEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnInfoSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jpnDetailInfoSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnSearchProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnInfoSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnInfoEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnSearchProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnDetailInfoSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Thoát Chương Trình", "Thoát", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            LoginView log = new LoginView();
            log.setVisible(true);
            setVisible(false);
            System.out.println("You have just pressed button 'CLOSE' ");
        }

    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        loadDataToTableByFindProduct();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblSearchProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchProductMouseClicked
        cboSoLuong.setEnabled(true);
        int index = tblSearchProduct.getSelectedRow();
        txtTenSP.setText((String) tblSearchProduct.getValueAt(index, 1));
        Double donGia = (Double) tblSearchProduct.getValueAt(index, 6);
        txtDonGia.setText(String.valueOf(donGia));
        txtTotalProduct.setText(String.valueOf(donGia));
        cboSoLuong.getSelectedIndex();
        btnAddProductInBag.setEnabled(true);
    }//GEN-LAST:event_tblSearchProductMouseClicked

    private void btnAddProductInBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductInBagActionPerformed
        // TODO add your handling code here:
        btnAddProductInBag.setEnabled(false);
        btnThanhToan.setEnabled(true);
        cboSoLuong.setEnabled(false);
        try {
            int index = tblSearchProduct.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblSell.getModel();
            Double donGia = (Double) tblSearchProduct.getValueAt(index, 6);
            int soLuong = Integer.parseInt((String) cboSoLuong.getSelectedItem());

            Object[] object = new Object[8];
            object[0] = tblSearchProduct.getValueAt(index, 0);
            object[1] = tblSearchProduct.getValueAt(index, 1);
            object[2] = tblSearchProduct.getValueAt(index, 2);
            object[3] = tblSearchProduct.getValueAt(index, 3);
            object[4] = tblSearchProduct.getValueAt(index, 4);
            object[5] = cboSoLuong.getSelectedItem();
            object[6] = tblSearchProduct.getValueAt(index, 6);
            object[7] = txtTotalProduct.getText();
            model.addRow(object);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAddProductInBagActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        autoIdHoaDon();
        autoIdKhachHang();

        txtTenSP.setText("");
        txtDonGia.setText("");
        txtTongTien.setText("");
        txtTotalProduct.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtTienKhachDua.setText("");
        txtTienThoiLai.setText("");
        DefaultTableModel modeTableSearchProduct = (DefaultTableModel) tblSearchProduct.getModel();
        modeTableSearchProduct.setRowCount(0);

        DefaultTableModel modeTableSell = (DefaultTableModel) tblSell.getModel();
        modeTableSell.setRowCount(0);
        buttonOption();
        btnSearch.setEnabled(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtSearchProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchProductMouseClicked
        // TODO add your handling code here:
        txtSearchProduct.setText("");
        DefaultTableModel model = (DefaultTableModel) tblSearchProduct.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_txtSearchProductMouseClicked

    private void cboSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSoLuongMouseClicked
        // TODO add your handling code here:
        int sizeChoose = Integer.parseInt((String) cboSoLuong.getSelectedItem());
        double donGia = Double.parseDouble(txtDonGia.getText());
        Double totalProduct = sizeChoose * donGia;
        txtTotalProduct.setText(String.valueOf(totalProduct));
        totalProduct();
    }//GEN-LAST:event_cboSoLuongMouseClicked

    private void cboSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSoLuongActionPerformed
        // TODO add your handling code here:
        int sizeChoose = Integer.parseInt((String) cboSoLuong.getSelectedItem());
        double donGia = Double.parseDouble(txtDonGia.getText());
        Double totalProduct = sizeChoose * donGia;
        txtTotalProduct.setText(String.valueOf(totalProduct));
        totalProduct();
    }//GEN-LAST:event_cboSoLuongActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        double tongTien = 0;
        for (int i = 0; i < tblSell.getRowCount(); i++) {
            double col = Double.parseDouble((String) tblSell.getValueAt(i, 7));
            tongTien += col;
        }
        txtTongTien.setText(String.valueOf(tongTien));
        btnTinhToan.setEnabled(true);
        btnInHoaDon.setEnabled(true);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTinhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhToanActionPerformed
        // TODO add your handling code here:
        try {

            if (Double.parseDouble(txtTienKhachDua.getText()) < Double.parseDouble(txtTongTien.getText()) || txtTienKhachDua.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Không Hợp Lệ, Vui lòng xem lại Hóa Đơn");
            } else {
                double tienThoiLai = Double.parseDouble(txtTienKhachDua.getText()) - Double.parseDouble(txtTongTien.getText());
                txtTienThoiLai.setText(String.valueOf(tienThoiLai));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không Hợp Lệ, Vui lòng xem lại Hóa Đơn");
        }
    }//GEN-LAST:event_btnTinhToanActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed

        try {
            if (txtTenKH.getText().equals("") && txtSDT.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên KH và SĐT Không được bỏ Trống, Vui lòng Kiểm Tra Lại");
            } else {
                addBill();
                addBill_detail();
                PrintHoaDon();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }


    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int index = tblSell.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) tblSell.getModel();
        model.removeRow(index);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblSellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSellMouseClicked
        // TODO add your handling code here:
        try {

            btnDelete.setEnabled(true);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSellMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCloseActionPerformed

    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchProductActionPerformed

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
            java.util.logging.Logger.getLogger(SellView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProductInBag;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTinhToan;
    private javax.swing.JComboBox cboSoLuong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpnDetailInfoSale;
    private javax.swing.JPanel jpnInfoEmployees;
    private javax.swing.JPanel jpnInfoSale;
    private javax.swing.JPanel jpnSearch;
    private javax.swing.JPanel jpnSearchProduct;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblIconDay;
    private javax.swing.JLabel lblIconInfoUser;
    private javax.swing.JLabel lblIconTime;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblRoleName;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTienKhachDua;
    private javax.swing.JLabel lblTienThoiLai;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTable tblSearchProduct;
    private javax.swing.JTable tblSell;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThoiLai;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTotalProduct;
    // End of variables declaration//GEN-END:variables
}
