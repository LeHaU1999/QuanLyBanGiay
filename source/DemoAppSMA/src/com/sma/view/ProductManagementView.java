/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.sma.config.JDBCConnection;
import com.sma.controller.EmployeesController;
import com.sma.controller.ProductController;
import com.sma.controller.RoleController;
import com.sma.entity.Employees;
import com.sma.entity.Product;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ProductManagementView extends javax.swing.JFrame {

    /**
     * Creates new form ProductManagement
     */
    public ProductManagementView() {
        initComponents();
        loadDataToCombobox();
        loadDataToTableByGiayNam();
        loadDataToTableByGiayNu();
        buttonEnable();
        textfieldEnable();
        this.setLocationRelativeTo(null);
    }

    public void textfieldEnable() {
        txtId.setText("");
        txtName.setText("");
        cboSize.setSelectedItem(null);
        txtSoLuong.setText("");
        radGiayNam.setSelected(false);
        radGiayNu.setSelected(false);
        cboDVT.setSelectedItem(null);
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        jdcNgayNhap.setDate(null);
        cboNSX.setSelectedItem(null);
    }

    //load data to combobox
    ArrayList<String> listCombobox = ProductController.loadDataToCombobox();

    public void loadDataToCombobox() {
        for (String item : listCombobox) {
            cboNSX.addItem(item.toString());
        }
    }

    public void loadDataToTableByGiayNam() {
        List<Product> listGiayNam = ProductController.getDataByGiayNam();
        DefaultTableModel model = (DefaultTableModel) tblGiayNam.getModel();
        // System.out.println("model");
        Object[] object = new Object[10];
        // System.out.println("oblect");
        for (int i = 0; i < listGiayNam.size(); i++) {
            object[0] = listGiayNam.get(i).getId();
            object[1] = listGiayNam.get(i).getName();
            object[2] = listGiayNam.get(i).getSize();
            object[3] = listGiayNam.get(i).getCount();
            object[4] = listGiayNam.get(i).getUnit();
            object[5] = listGiayNam.get(i).getMoneyInput();
            object[6] = listGiayNam.get(i).getMoneyOutput();
            object[7] = listGiayNam.get(i).getNgayNhap();
            object[8] = listGiayNam.get(i).getId_producer();
            object[9] = listGiayNam.get(i).getType();
            model.addRow(object);
        }
    }

    public void loadDataToTableByGiayNu() {
        List<Product> listGiayNu = ProductController.getDataByGiayNu();
        DefaultTableModel model = (DefaultTableModel) tblGiayNu.getModel();
        // System.out.println("model");
        Object[] object = new Object[10];
        // System.out.println("oblect");
        for (int i = 0; i < listGiayNu.size(); i++) {
            object[0] = listGiayNu.get(i).getId();
            object[1] = listGiayNu.get(i).getName();
            object[2] = listGiayNu.get(i).getSize();
            object[3] = listGiayNu.get(i).getCount();
            object[4] = listGiayNu.get(i).getUnit();
            object[5] = listGiayNu.get(i).getMoneyInput();
            object[6] = listGiayNu.get(i).getMoneyOutput();
            object[7] = listGiayNu.get(i).getNgayNhap();
            object[8] = listGiayNu.get(i).getId_producer();
            object[9] = listGiayNu.get(i).getType();
            model.addRow(object);
        }
    }

    public void autoId() {
        Connection conn = null;
        try {
            conn = JDBCConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Max(id_product) FROM products");
            rs.next();
            if (rs.getString("Max(id_product)") == null) {
                txtId.setText("PRDT001");
            } else {
                int id = Integer.parseInt(rs.getString("Max(id_product)").substring(5, rs.getString("Max(id_product)").length()));
                id++;
                txtId.setText("PRDT00" + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buttonEnable() {
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public void XuatDanhSachSanPhamGiayNu() {
        File file = new File("danhsachgiaynu.txt");
        file.delete();
        int rowCount = tblGiayNu.getRowCount();
        System.out.println(rowCount);
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("danhsachgiaynu.txt"), "UTF8"));
            b.write("\t\t\t\t\tDANH SACH GIÀY NỮ\n");
            b.write("\t\t\t\t\t*******************\n");
            b.write("\n");
            b.write("\tTổng Số Sản Phẩm: " + String.valueOf(rowCount) + "\n");
            b.write("\n------------------------------------------------------------------------------------------------------------------------------------------\n");

            b.write("   " + "STT" + "\t" + "ID_SP" + "\t" + "NAME" + "\t\t\t" + "Size" + "\t" + "SL" + "\t" + "ĐVT" + "\t" + "Nhập" + "\t" + "Bán" + "\t" + "Ngày Nhập" + "\t" + "NSX" + "\n");

            int index = tblGiayNu.getRowCount();
            for (int i = 0; i < index; i++) {
                
                String a0 = String.valueOf(i + 1);
                String a1 = (String) tblGiayNu.getValueAt(i, 0);
                String a2 = (String) tblGiayNu.getValueAt(i, 1);
                
                int  a3 = (int) tblGiayNu.getValueAt(i, 2);
                String aa3 = String.valueOf(a3);
                
                int  a4 = (int) tblGiayNu.getValueAt(i, 3);
                String aa4 = String.valueOf(a4);
                
                String a5 = (String) tblGiayNu.getValueAt(i, 4);
                
                double a6 = (double) tblGiayNu.getValueAt(i, 5);
                String aa6 = String.valueOf(a6);
                
                double a7 = (double) tblGiayNu.getValueAt(i, 6);
                String aa7 = String.valueOf(a7);
                
                String a8 = (String) tblGiayNu.getValueAt(i, 7);
                String a9 = (String) tblGiayNu.getValueAt(i, 8);
               

            

                String s0 = a0;
                String s1 = a1;
                String s2 = a2;
                String s3 = aa3;
                String s4 = aa4;
                String s5 = a5;
                String s6 = aa6;
                String s7 = aa7;
                String s8 = a8;
                String s9 = a9;
                b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
                b.write("   " + s0 + "\t" + s1 + "\t" + s2 + "\t" + s3 + "\t" + s4 + "\t" + s5 + "\t" + s6 + "\t" + s7 + "\t" + s8 + "\t" + s9 + "\n");
            }
            b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  danhsachgiaynu.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xuất Cái j mà xuất, Lỗi rồi");
        }
    }
    
    
    public void XuatDanhSachSanPhamGiayNam() {
        File file = new File("danhsachgiaynam.txt");
        file.delete();
        int rowCount = tblGiayNu.getRowCount();
        System.out.println(rowCount);
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("danhsachgiaynam.txt"), "UTF8"));
            b.write("\t\t\t\t\tDANH SACH GIÀY NAM\n");
            b.write("\t\t\t\t\t*******************\n");
            b.write("\n");
            b.write("\tTổng Số Sản Phẩm: " + String.valueOf(rowCount) + "\n");
            b.write("\n------------------------------------------------------------------------------------------------------------------------------------------\n");

            b.write("   " + "STT" + "\t" + "ID_SP" + "\t" + "NAME" + "\t\t\t" + "Size" + "\t" + "SL" + "\t" + "ĐVT" + "\t" + "Nhập" + "\t" + "Bán" + "\t" + "Ngày Nhập" + "\t" + "NSX" + "\n");

            int index = tblGiayNam.getRowCount();
            for (int i = 0; i < index; i++) {
                
                String a0 = String.valueOf(i + 1);
                String a1 = (String) tblGiayNam.getValueAt(i, 0);
                String a2 = (String) tblGiayNam.getValueAt(i, 1);
                
                int  a3 = (int) tblGiayNam.getValueAt(i, 2);
                String aa3 = String.valueOf(a3);
                
                int  a4 = (int) tblGiayNam.getValueAt(i, 3);
                String aa4 = String.valueOf(a4);
                
                String a5 = (String) tblGiayNam.getValueAt(i, 4);
                
                double a6 = (double) tblGiayNam.getValueAt(i, 5);
                String aa6 = String.valueOf(a6);
                
                double a7 = (double) tblGiayNam.getValueAt(i, 6);
                String aa7 = String.valueOf(a7);
                
                String a8 = (String) tblGiayNam.getValueAt(i, 7);
                String a9 = (String) tblGiayNam.getValueAt(i, 8);
               

            

                String s0 = a0;
                String s1 = a1;
                String s2 = a2;
                String s3 = aa3;
                String s4 = aa4;
                String s5 = a5;
                String s6 = aa6;
                String s7 = aa7;
                String s8 = a8;
                String s9 = a9;
                b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
                b.write("   " + s0 + "\t" + s1 + "\t" + s2 + "\t" + s3 + "\t" + s4 + "\t" + s5 + "\t" + s6 + "\t" + s7 + "\t" + s8 + "\t" + s9 + "\n");
            }
            b.write("------------------------------------------------------------------------------------------------------------------------------------------\n");
            b.close();
            Runtime run = Runtime.getRuntime();
            run.exec("notepad  danhsachgiaynam.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xuất CC, Lỗi rồi");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngLoaiGiay = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jpnBanner = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnXuatDanhSach = new javax.swing.JButton();
        jpnInfoProduct = new javax.swing.JPanel();
        jpnDetailInformation = new javax.swing.JPanel();
        lblMaSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblLoaiGiay = new javax.swing.JLabel();
        lblDonViTinh = new javax.swing.JLabel();
        lblGiaNhap = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblNgayNhap = new javax.swing.JLabel();
        lblNSX = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        radGiayNam = new javax.swing.JRadioButton();
        radGiayNu = new javax.swing.JRadioButton();
        txtGiaNhap = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAddNewProduct = new javax.swing.JButton();
        cboDVT = new javax.swing.JComboBox();
        txtGiaBan = new javax.swing.JTextField();
        cboNSX = new javax.swing.JComboBox();
        lblThemNSX = new javax.swing.JLabel();
        lblGoToHomeScreen = new javax.swing.JLabel();
        jdcNgayNhap = new com.toedter.calendar.JDateChooser();
        cboSize = new javax.swing.JComboBox();
        jpnPhanLoaiSanPham = new javax.swing.JTabbedPane();
        jpnGiayNu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGiayNu = new javax.swing.JTable();
        jpnGiayNam = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGiayNam = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jpnBanner.setBackground(new java.awt.Color(255, 255, 255));
        jpnBanner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DANH SÁCH SẢN PHẨM");

        btnXuatDanhSach.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        btnXuatDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-print.png"))); // NOI18N
        btnXuatDanhSach.setText("Xuất Danh Sách");
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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnBannerLayout.setVerticalGroup(
            jpnBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBannerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnXuatDanhSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnInfoProduct.setBackground(new java.awt.Color(0, 76, 128));
        jpnInfoProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jpnDetailInformation.setBackground(new java.awt.Color(242, 242, 242));
        jpnDetailInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMaSP.setText("Mã SP:");

        lblTenSP.setText("Tên SP:");

        lblSize.setText("Size:");

        lblSoLuong.setText("Số Lượng:");

        lblLoaiGiay.setText("Loại");

        lblDonViTinh.setText("ĐVT:");

        lblGiaNhap.setText("Giá Nhập:");

        lblGiaBan.setText("Giá Bán:");

        lblNgayNhap.setText("Ngày Nhập:");

        lblNSX.setText("NSX:");

        btngLoaiGiay.add(radGiayNam);
        radGiayNam.setText("Giày Nam");

        btngLoaiGiay.add(radGiayNu);
        radGiayNu.setText("Giày Nữ");

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-save.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-updatepng.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAddNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-add-new-product.png"))); // NOI18N
        btnAddNewProduct.setText("Add New Product");
        btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewProductActionPerformed(evt);
            }
        });

        cboDVT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đôi", "Cặp", "Cái" }));

        cboNSX.setMaximumRowCount(10);
        cboNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNSXActionPerformed(evt);
            }
        });

        lblThemNSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-plus.png"))); // NOI18N
        lblThemNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemNSXMouseClicked(evt);
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

        cboSize.setMaximumRowCount(15);
        cboSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45" }));
        cboSize.setMinimumSize(new java.awt.Dimension(49, 22));

        javax.swing.GroupLayout jpnDetailInformationLayout = new javax.swing.GroupLayout(jpnDetailInformation);
        jpnDetailInformation.setLayout(jpnDetailInformationLayout);
        jpnDetailInformationLayout.setHorizontalGroup(
            jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInformationLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddNewProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaSP)
                                    .addComponent(lblTenSP)
                                    .addComponent(lblSize)
                                    .addComponent(lblSoLuong)
                                    .addComponent(lblLoaiGiay)
                                    .addComponent(lblGiaNhap)
                                    .addComponent(lblNgayNhap)
                                    .addComponent(lblNSX)
                                    .addComponent(btnSave)
                                    .addComponent(lblDonViTinh)
                                    .addComponent(lblGiaBan))
                                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(btnUpdate)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnDelete))
                                    .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInformationLayout.createSequentialGroup()
                                                .addComponent(cboNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblThemNSX))
                                            .addComponent(txtId)
                                            .addComponent(txtName)
                                            .addComponent(txtSoLuong)
                                            .addComponent(txtGiaNhap)
                                            .addComponent(txtGiaBan)
                                            .addComponent(jdcNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                                                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(radGiayNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cboDVT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(radGiayNu))
                                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDetailInformationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnDetailInformationLayout.setVerticalGroup(
            jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDetailInformationLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSP)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenSP)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSize)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoLuong)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiGiay)
                    .addComponent(radGiayNam)
                    .addComponent(radGiayNu))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDonViTinh)
                    .addComponent(cboDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblGiaNhap)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaBan)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNgayNhap)
                    .addComponent(jdcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThemNSX)
                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNSX)
                        .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnDetailInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddNewProduct)
                .addGap(20, 20, 20)
                .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnInfoProductLayout = new javax.swing.GroupLayout(jpnInfoProduct);
        jpnInfoProduct.setLayout(jpnInfoProductLayout);
        jpnInfoProductLayout.setHorizontalGroup(
            jpnInfoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoProductLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jpnDetailInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jpnInfoProductLayout.setVerticalGroup(
            jpnInfoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInfoProductLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jpnDetailInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jpnPhanLoaiSanPham.setBackground(new java.awt.Color(242, 242, 242));

        jpnGiayNu.setBackground(new java.awt.Color(255, 255, 255));
        jpnGiayNu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 76, 128), 2));
        jpnGiayNu.setEnabled(false);

        tblGiayNu.setBackground(new java.awt.Color(242, 242, 242));
        tblGiayNu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Size", "SL", "ĐVT", "Giá Nhập", "Giá Bán", "Ngày Nhập", "Mã NSX"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGiayNu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiayNuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGiayNu);

        javax.swing.GroupLayout jpnGiayNuLayout = new javax.swing.GroupLayout(jpnGiayNu);
        jpnGiayNu.setLayout(jpnGiayNuLayout);
        jpnGiayNuLayout.setHorizontalGroup(
            jpnGiayNuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnGiayNuLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnGiayNuLayout.setVerticalGroup(
            jpnGiayNuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jpnPhanLoaiSanPham.addTab("GIÀY NỮ", jpnGiayNu);

        jpnGiayNam.setBackground(new java.awt.Color(255, 255, 255));
        jpnGiayNam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 28, 37), 2));

        tblGiayNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Size", "SL", "ĐVT", "Giá Nhập", "Giá Bán", "Ngày Nhập", "Mã NSX"
            }
        ));
        tblGiayNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiayNamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGiayNam);

        javax.swing.GroupLayout jpnGiayNamLayout = new javax.swing.GroupLayout(jpnGiayNam);
        jpnGiayNam.setLayout(jpnGiayNamLayout);
        jpnGiayNamLayout.setHorizontalGroup(
            jpnGiayNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnGiayNamLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnGiayNamLayout.setVerticalGroup(
            jpnGiayNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jpnPhanLoaiSanPham.addTab("GIÀY NAM", jpnGiayNam);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnInfoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnPhanLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpnBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnInfoProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnPhanLoaiSanPham))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblGoToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoToHomeScreenMouseClicked
        // TODO add your handling code here:
        HomeScreen home = new HomeScreen();
        home.setVisible(true);
        setVisible(false);
        System.out.println("You have just pressed the button 'Go To HomeScreen' ");
    }//GEN-LAST:event_lblGoToHomeScreenMouseClicked

    private void lblThemNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemNSXMouseClicked
        // TODO add your handling code here:
        ProducerView prdcV = new ProducerView();
        prdcV.setVisible(true);
    }//GEN-LAST:event_lblThemNSXMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            System.out.println("1");
            int size = Integer.parseInt((String) cboSize.getSelectedItem());
            System.out.println("2");
            int countProduct = Integer.parseInt(cboSize.getSelectedItem().toString());
            System.out.println("3");
            String type = null;
            if (radGiayNam.isSelected()) {
                type = "Giày Nam";
            } else {
                type = "Giày Nữ";
            }
            System.out.println("4");
            String dvt = (String) cboDVT.getSelectedItem();
            System.out.println("5");
            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            System.out.println("6");
            double giaBan = Double.parseDouble(txtGiaBan.getText());
            System.out.println("7");
            String id_producer = cboNSX.getSelectedItem().toString();

            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "id sản phẩm không được bỏ trống");
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "tên sản phẩm không được bỏ trống");
            } else if (txtSoLuong.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "số lượng sản phẩm không được bỏ trống");
            } else if (txtGiaNhap.equals("")) {
                JOptionPane.showMessageDialog(null, "giá nhập sản phẩm không được bỏ trống");
            } else if (txtGiaBan.equals("")) {
                JOptionPane.showMessageDialog(null, "giá bán sản phẩm không được bỏ trống");
            } else {

                SimpleDateFormat dayInput = new SimpleDateFormat("MMM d, yyyy");
                String ngayNhap = dayInput.format(jdcNgayNhap.getDate());

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setSize(size);
                product.setCount(countProduct);
                product.setType(type);
                product.setUnit(dvt);
                product.setMoneyInput(giaNhap);
                product.setMoneyOutput(giaBan);
                product.setNgayNhap(ngayNhap);
                product.setId_producer(id_producer);

                ProductController.addProduct(product);
                JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm thành công");

                if (radGiayNam.isSelected()) {
                    DefaultTableModel model = (DefaultTableModel) tblGiayNam.getModel();
                    model.setRowCount(0);
                    loadDataToTableByGiayNam();
                    autoId();
                } else {
                    DefaultTableModel model = (DefaultTableModel) tblGiayNu.getModel();
                    model.setRowCount(0);
                    loadDataToTableByGiayNu();
                    autoId();
                }

                txtName.setText("");
                cboSize.setSelectedItem(null);
                txtSoLuong.setText("");
                radGiayNam.setSelected(false);
                radGiayNu.setSelected(false);
                cboDVT.setSelectedItem(null);
                txtGiaNhap.setText("");
                txtGiaBan.setText("");
                jdcNgayNhap.setCalendar(null);
                cboNSX.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Thêm sản phẩm thất bại, Vui lòng điền đủ thông tin hoặc xem lại định dạng ngày tháng...");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        // TODO add your handling code here:
        autoId();
        radGiayNam.setEnabled(true);
        radGiayNu.setEnabled(true);

        txtName.setText("");
        cboSize.setSelectedIndex(0);
        txtSoLuong.setText("0");
        radGiayNam.setSelected(false);
        radGiayNu.setSelected(false);
        cboDVT.setSelectedIndex(0);
        txtGiaNhap.setText("0");
        txtGiaBan.setText("0");
        jdcNgayNhap.setCalendar(null);
        cboNSX.setSelectedIndex(0);

        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);


    }//GEN-LAST:event_btnAddNewProductActionPerformed

    private void tblGiayNuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayNuMouseClicked
        // TODO add your handling code here:
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);

        try {
            int index = tblGiayNu.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblGiayNu.getModel();
            txtId.setText(model.getValueAt(index, 0).toString());
            txtName.setText(model.getValueAt(index, 1).toString());

            String size = model.getValueAt(index, 2).toString().trim();
            switch (size) {
                case "35": {
                    cboSize.setSelectedIndex(0);
                    break;
                }
                case "36": {
                    cboSize.setSelectedIndex(1);
                    break;
                }
                case "37": {
                    cboSize.setSelectedIndex(2);
                    break;
                }
                case "38": {
                    cboSize.setSelectedIndex(3);
                    break;
                }
                case "39": {
                    cboSize.setSelectedIndex(4);
                    break;
                }
                case "40": {
                    cboSize.setSelectedIndex(5);
                    break;
                }
                case "41": {
                    cboSize.setSelectedIndex(6);
                    break;
                }
                case "42": {
                    cboSize.setSelectedIndex(7);
                    break;
                }
                case "43": {
                    cboSize.setSelectedIndex(8);
                    break;
                }
                case "44": {
                    cboSize.setSelectedIndex(9);
                    break;
                }
                case "45": {
                    cboSize.setSelectedIndex(10);
                    break;
                }
            }
            txtSoLuong.setText(model.getValueAt(index, 3).toString());
            radGiayNu.setSelected(true);
            radGiayNu.setEnabled(false);
            radGiayNam.setEnabled(false);

            String dvt = model.getValueAt(index, 4).toString().trim();
            switch (dvt) {
                case "Đôi":
                    cboDVT.setSelectedIndex(0);
                    break;
                case "Cặp":
                    cboDVT.setSelectedIndex(1);
                    break;
                case "Cái":
                    cboDVT.setSelectedIndex(2);
                    break;
            }
            txtGiaNhap.setText(model.getValueAt(index, 5).toString());
            txtGiaBan.setText(model.getValueAt(index, 6).toString());

            Date ngayNhap = new SimpleDateFormat("MMM d, yyyy").parse((String) model.getValueAt(index, 7));
            jdcNgayNhap.setDate(ngayNhap);

            String nsx = model.getValueAt(index, 8).toString().trim();
            switch (nsx) {
                case "PDC01":
                    cboNSX.setSelectedIndex(0);
                    break;
                case "PDC02":
                    cboNSX.setSelectedIndex(1);
                    break;
                case "PDC03":
                    cboNSX.setSelectedIndex(2);
                    break;
                case "PDC04":
                    cboNSX.setSelectedIndex(3);
                    break;
                case "PDC05":
                    cboNSX.setSelectedIndex(4);
                    break;
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProductManagementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblGiayNuMouseClicked

    private void tblGiayNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiayNamMouseClicked
        // TODO add your handling code here:
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        try {
            int index = tblGiayNam.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblGiayNam.getModel();
            txtId.setText(model.getValueAt(index, 0).toString());
            txtName.setText(model.getValueAt(index, 1).toString());

            String size = model.getValueAt(index, 2).toString().trim();
            switch (size) {
                case "35": {
                    cboSize.setSelectedIndex(0);
                    break;
                }
                case "36": {
                    cboSize.setSelectedIndex(1);
                    break;
                }
                case "37": {
                    cboSize.setSelectedIndex(2);
                    break;
                }
                case "38": {
                    cboSize.setSelectedIndex(3);
                    break;
                }
                case "39": {
                    cboSize.setSelectedIndex(4);
                    break;
                }
                case "40": {
                    cboSize.setSelectedIndex(5);
                    break;
                }
                case "41": {
                    cboSize.setSelectedIndex(6);
                    break;
                }
                case "42": {
                    cboSize.setSelectedIndex(7);
                    break;
                }
                case "43": {
                    cboSize.setSelectedIndex(8);
                    break;
                }
                case "44": {
                    cboSize.setSelectedIndex(9);
                    break;
                }
                case "45": {
                    cboSize.setSelectedIndex(10);
                    break;
                }
            }
            txtSoLuong.setText(model.getValueAt(index, 3).toString());
            radGiayNam.setSelected(true);
            radGiayNu.setEnabled(false);
            radGiayNam.setEnabled(false);

            String dvt = model.getValueAt(index, 4).toString().trim();
            switch (dvt) {
                case "Đôi":
                    cboDVT.setSelectedIndex(0);
                    break;
                case "Cặp":
                    cboDVT.setSelectedIndex(1);
                    break;
                case "Cái":
                    cboDVT.setSelectedIndex(2);
                    break;
            }
            txtGiaNhap.setText(model.getValueAt(index, 5).toString());
            txtGiaBan.setText(model.getValueAt(index, 6).toString());

            Date ngayNhap = new SimpleDateFormat("MMM d, yyyy").parse((String) model.getValueAt(index, 7));
            jdcNgayNhap.setDate(ngayNhap);

            String nsx = model.getValueAt(index, 8).toString().trim();
            switch (nsx) {
                case "PDC01":
                    cboNSX.setSelectedIndex(0);
                    break;
                case "PDC02":
                    cboNSX.setSelectedIndex(1);
                    break;
                case "PDC03":
                    cboNSX.setSelectedIndex(2);
                    break;
                case "PDC04":
                    cboNSX.setSelectedIndex(3);
                    break;
                case "PDC05":
                    cboNSX.setSelectedIndex(4);
                    break;
            }

        } catch (ParseException ex) {
            Logger.getLogger(ProductManagementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblGiayNamMouseClicked

    private void cboNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNSXActionPerformed
        // TODO add your handling code here:
        // loadDataToCombobox();
    }//GEN-LAST:event_cboNSXActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {

            int option = JOptionPane.showConfirmDialog(null, "Are You Sure", "DELETE", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                if (radGiayNu.isSelected()) {
                    int rowIndex = tblGiayNu.getSelectedRow();
                    String id_product = tblGiayNu.getModel().getValueAt(rowIndex, 0).toString().trim();
                    ProductController.deleteProduct(id_product);
                    DefaultTableModel model = (DefaultTableModel) tblGiayNu.getModel();
                    model.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Deleted Sucessfully!");
                    loadDataToTableByGiayNu();
                } else {
                    int rowIndex = tblGiayNam.getSelectedRow();
                    String id_product = tblGiayNam.getModel().getValueAt(rowIndex, 0).toString().trim();
                    ProductController.deleteProduct(id_product);
                    DefaultTableModel model = (DefaultTableModel) tblGiayNam.getModel();
                    model.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Deleted Sucessfully!");
                    loadDataToTableByGiayNam();
                }
                buttonEnable();
                textfieldEnable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm, ràng buột trong hóa đơn ...Khong The Xoa San Pham, Rang Buoc Hoa Don");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            int size = Integer.parseInt((String) cboSize.getSelectedItem());
            int countProduct = Integer.parseInt(cboSize.getSelectedItem().toString());
            String type = null;
            if (radGiayNam.isSelected()) {
                type = "Giày Nam";
            } else {
                type = "Giày Nữ";
            }
            String dvt = (String) cboDVT.getSelectedItem();
            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            double giaBan = Double.parseDouble(txtGiaBan.getText());
            String id_producer = cboNSX.getSelectedItem().toString();

            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "id sản phẩm không được bỏ tróng");
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "tên sản phẩm không được bỏ trống");
            } else if (txtSoLuong.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "số lượng sản phẩm không được bỏ trống");
            } else if (txtGiaNhap.equals("")) {
                JOptionPane.showMessageDialog(null, "giá nhập sản phẩm không được bỏ trống");
            } else if (txtGiaBan.equals("")) {
                JOptionPane.showMessageDialog(null, "giá bán sản phẩm không được bỏ trống");
            } else {

                SimpleDateFormat dayInput = new SimpleDateFormat("MMM d, yyyy");
                String ngayNhap = dayInput.format(jdcNgayNhap.getDate());

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setSize(size);
                product.setCount(countProduct);
                product.setType(type);
                product.setUnit(dvt);
                product.setMoneyInput(giaNhap);
                product.setMoneyOutput(giaBan);
                product.setNgayNhap(ngayNhap);
                product.setId_producer(id_producer);

                ProductController.addProduct(product);
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");

                if (radGiayNu.isSelected()) {
                    int rowIndex = tblGiayNu.getSelectedRow();
                    String id_product = tblGiayNu.getModel().getValueAt(rowIndex, 0).toString();
                    ProductController.updateProduct(product, id_product);
                    JOptionPane.showMessageDialog(null, "Update Product Sucessfully!");
                    DefaultTableModel model = (DefaultTableModel) tblGiayNu.getModel();
                    model.setRowCount(0);
                    loadDataToTableByGiayNu();
                } else {
                    int rowIndex = tblGiayNam.getSelectedRow();
                    String id_product = tblGiayNam.getModel().getValueAt(rowIndex, 0).toString();
                    ProductController.updateProduct(product, id_product);
                    JOptionPane.showMessageDialog(null, "Update Product Sucessfully!");
                    DefaultTableModel model = (DefaultTableModel) tblGiayNam.getModel();
                    model.setRowCount(0);
                    loadDataToTableByGiayNam();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cập Nhật sảm phẩm thất bại hoặc vui lòng xem lại định dạng ngày tháng");
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnXuatDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachActionPerformed
        // TODO add your handling code here:
        XuatDanhSachSanPhamGiayNu();
        XuatDanhSachSanPhamGiayNam();
    }//GEN-LAST:event_btnXuatDanhSachActionPerformed

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
            java.util.logging.Logger.getLogger(ProductManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductManagementView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXuatDanhSach;
    private javax.swing.ButtonGroup btngLoaiGiay;
    private javax.swing.JComboBox cboDVT;
    private javax.swing.JComboBox cboNSX;
    private javax.swing.JComboBox cboSize;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcNgayNhap;
    private javax.swing.JPanel jpnBanner;
    private javax.swing.JPanel jpnDetailInformation;
    private javax.swing.JPanel jpnGiayNam;
    private javax.swing.JPanel jpnGiayNu;
    private javax.swing.JPanel jpnInfoProduct;
    private javax.swing.JTabbedPane jpnPhanLoaiSanPham;
    private javax.swing.JLabel lblDonViTinh;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblGoToHomeScreen;
    private javax.swing.JLabel lblLoaiGiay;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblNSX;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThemNSX;
    private javax.swing.JRadioButton radGiayNam;
    private javax.swing.JRadioButton radGiayNu;
    private javax.swing.JTable tblGiayNam;
    private javax.swing.JTable tblGiayNu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
